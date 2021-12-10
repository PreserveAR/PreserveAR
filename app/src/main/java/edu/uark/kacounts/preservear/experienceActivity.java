package edu.uark.kacounts.preservear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class experienceActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvDescription;
    ImageView ivImage;
    ImageButton btnAudio;
    TextView tvAudioDesc;
    Boolean audioOn; // keeps track if the audio is playing or not
    MediaPlayer mp; // allows audio to play
    String url = "https://drive.google.com/file/d/1w2duAz0gVv_Jq1NoHIFtt2GNGAWj_HoC/view?usp=sharing"; // location of our audio's URL if it exists
    static final String audioPlaying = "Pause our audio recording";
    static final String audioPause = "Resume our audio recording";
    static final String audioPlay = "Play our audio recording";
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        tvTitle = findViewById(R.id.tvExperienceTitle);
        ivImage = findViewById(R.id.ivExperienceImage);
        btnAudio = findViewById(R.id.btnAudioControl);
        tvAudioDesc = findViewById(R.id.tvAudio);
        tvDescription = findViewById(R.id.tvExperienceDesciption);
        audioOn = false;
        Intent callingIntent = this.getIntent();
        title = callingIntent.getStringExtra("name");
        Log.d("experience", "title = " + title);

        // find audio file in database
        if (title.equals("Battlefield park")) {
            tvTitle.setText(R.string.title1);
            ivImage.setImageResource(R.drawable.battlefieldparkmarker01);
            tvDescription.setText(R.string.desc1);
        }
        else if (title.equals("Battle of prairie grove")) {
            tvTitle.setText(R.string.title2);
            ivImage.setImageResource(R.drawable.battlefieldparkmarker02);
            tvDescription.setText(R.string.desc2);
        }
        else if (title.equals("March of the armies")) {
            tvTitle.setText(R.string.title3);
            ivImage.setImageResource(R.drawable.battlefieldparkmarker03);
            tvDescription.setText(R.string.desc3);
        }
        // audio file found!
        if (url != "x") {
        }
        else {
            btnAudio.setVisibility(View.INVISIBLE);
            tvAudioDesc.setVisibility(View.INVISIBLE);
        }
    }

    // make the button produce audio
    public void audioPushed(View v) {
        // audio isn't playing, start it
        if (!audioOn) {
            Log.d("ExperienceActivity", "Going to start audio!");
            audioOn = true;
            btnAudio.setImageResource(android.R.drawable.ic_media_pause);
            tvAudioDesc.setText(audioPlaying);
            // start the audio
//            playAudio();
        }
        // audio is playing, pause it
        else {
            Log.d("ExperienceActivity", "Going to stop audio!");
            audioOn = false;
            tvAudioDesc.setText(audioPause);
            btnAudio.setImageResource(android.R.drawable.ic_media_play);
//            mp.pause();
        }
    }

    // make function that plays our audio
    void playAudio() {
        mp = new MediaPlayer();

//        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // get the audio source
        try {
            mp.setDataSource(url);
            mp.prepareAsync();
//            mp.start();
        } catch (IOException e) {
            Log.d("ExperienceActivity", "Audio failed to load!");
        }
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mp.start();
            }
        });
//        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//
//            }
//        });


    }

//    @Override
//    protected void onDestroy() {
//        mp.release();
//        mp = null;
//        super.onDestroy();
//    }
public void setPicture(String photoPath) {
    // Get the dimensions of the View
    int targetW = ivImage.getWidth();
    int targetH = ivImage.getHeight();

    // Get the dimensions of the bitmap
    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
    bmOptions.inJustDecodeBounds = true;

    BitmapFactory.decodeFile(photoPath, bmOptions);

    int photoW = bmOptions.outWidth;
    int photoH = bmOptions.outHeight;

    // Determine how much to scale down the image
    int scaleFactor = Math.max(1, Math.min(photoW / targetW, photoH / targetH));

    // Decode the image file into a Bitmap sized to fill the View
    bmOptions.inJustDecodeBounds = false;
    bmOptions.inSampleSize = scaleFactor;
    bmOptions.inPurgeable = true;

    Bitmap bitmap = BitmapFactory.decodeFile(photoPath, bmOptions);
    ivImage.setImageBitmap(bitmap);
}
}