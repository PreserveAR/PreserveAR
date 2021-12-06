package edu.uark.kacounts.preservear.PhotoActivity;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import edu.uark.kacounts.preservear.Data.Photo;
import edu.uark.kacounts.preservear.Data.PhotoDataSource;
import edu.uark.kacounts.preservear.PhotoActivity.TakePhotoContract;

public class TakePhotoPresenter implements TakePhotoContract.Presenter{

    private TakePhotoContract.View mView;
    private PhotoDataSource mModel;
    private Photo photo;

    @Override
    public void startPresenter() {
        mView.setPresenter(this);
    }

    @Override
    public void setView(TakePhotoContract.View view) {
        mView = view;
    }

    @Override
    public void notifyAddClicked() {
        mView.captureNewPhoto();
    }

    @Override
    public void setModel(PhotoDataSource model) {
        mModel = model;
    }

    @Override
    public void notifyPictureCaptured(String photoPath) {
        Photo photo = new Photo();
        photo.setFilename(photoPath);
//        photo.setLatitude(MapsActivity.Latitude);
//        photo.setLongitude(MapsActivity.Longitude);
        photo.setLatitude(0.0);
        photo.setLongitude(0.0);
        Log.d("Presenter", "fileName = " + photoPath);
        photo.setComment("Photo!");
        mModel.createPhoto(photo, new PhotoDataSource.CreatePhotoCallback() {
            @Override
            public void onPhotoCreated(int id) {
                mView.photoAdded(id);
            }

            @Override
            public void onPhotoCreateFail() {
                return;
            }
        });
        mView.setPicture(photoPath);
    }

    @Override
    public Photo getPhoto(int id) {
        mModel.getPhoto(id, new PhotoDataSource.GetPhotoCallback() {
            @Override
            public void onPhotoLoaded(Photo photos) {
                photo = photos;
                Log.d("presenter", String.valueOf(photo.getFilename()));
            }

            @Override
            public void onDataNotAvailable() {
                Log.e("TakePhotoPresenter","Data not loaded");
            }
        });
        return photo;
    }
}
