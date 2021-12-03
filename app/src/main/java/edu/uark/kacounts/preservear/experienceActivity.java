package edu.uark.kacounts.preservear;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        tvTitle = findViewById(R.id.tvTitle);
        ivImage = findViewById(R.id.ivExperienceImage);
        btnAudio = findViewById(R.id.btnAudioControl);
        tvAudioDesc = findViewById(R.id.tvAudio);
        tvDescription = findViewById(R.id.tvDescription);
        audioOn = false;

        // find audio file in database


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
            playAudio();
        }
        // audio is playing, pause it
        else {
            Log.d("ExperienceActivity", "Going to stop audio!");
            audioOn = false;
            tvAudioDesc.setText(audioPause);
            btnAudio.setImageResource(android.R.drawable.ic_media_play);
            mp.pause();
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

    @Override
    protected void onDestroy() {
        mp.release();
        mp = null;
        super.onDestroy();
    }
}