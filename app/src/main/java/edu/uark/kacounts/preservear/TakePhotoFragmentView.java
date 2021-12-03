package edu.uark.kacounts.preservear;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class TakePhotoFragmentView extends Fragment implements TakePhotoContract.View {

    private TakePhotoContract.Presenter mPresenter;
    ActivityResultLauncher<Intent> mActivityResultLauncher;
    String currentPhotoPath;
    ImageView imageView;
    Button btnSavePhoto;
    EditText title;
    EditText description;

    public TakePhotoFragmentView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        mPresenter.notifyPictureCaptured(currentPhotoPath);
                    }
                });

//        setContentView(R.layout.activity_preserve_description);
//        getSupportActionBar().setTitle("User Pics of AR");
//        btnSavePhoto = (Button) getView().findViewById(R.id.btnSavePhoto);
//        title = (EditText) getView().findViewById(R.id.etTitle);
//        description = getView().findViewById(R.id.etMessage);
    }

    @Override
    public void setPicture(String photoPath) {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.max(1, Math.min(photoW / targetW, photoH / targetH));

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(photoPath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }

    // lets us know that the photo has been added to the database
    @Override
    public void photoAdded(int id) {
        Toast.makeText(getActivity(), "Photo Created with id: " + id, Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_take_photo_view, container, false);
        //sends the user to capture a photo right away
        captureNewPhoto();
        imageView = root.findViewById(R.id.imageView3);
        return root;
    }

    @Override
    public void setPresenter(TakePhotoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    // this method sends user to capture a photo once finished it shows the user the photo that has been taken
    @Override
    public void captureNewPhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "edu.uark.kacounts.preservear.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                mActivityResultLauncher.launch(takePictureIntent);
            }
        }
    }

    // creates tehe image to be saved
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    // save photo uploads user's photo to database and then hides itself and prevents editing
    public void savePhoto(View v) {
        Log.d("PreserveActivity", "User done editing!");
        // upload to our database!

        // hide button
        btnSavePhoto.setVisibility(View.INVISIBLE);
        // make editing stop
        title.setEnabled(false);
        description.setEnabled(false);
    }
}