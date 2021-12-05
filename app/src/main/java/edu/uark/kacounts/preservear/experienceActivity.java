package edu.uark.kacounts.preservear;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class experienceActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvDescription;
    ImageView ivImage;
    ImageButton btnAudio;
    TextView tvAudioDesc;
    Boolean audioOn; // keeps track if the audio is playing or not
    MediaPlayer mp; // allows audio to play
    Button btnNext; // allows user to go to next image/description in current experience
    Button btnPrev; // allows user to go to previous image/description in current experience
    String imageURL1 = "https://drive.google.com/file/d/1Jz19Mhlg46js0dPQ74aC6Sd35WkWYwf8/view?usp=sharing";
    String imageURL = "http://www.google.iq/imgres?hl=en&biw=1366&bih=667&tbm=isch&tbnid=HjzjsaANDXVR9M:&imgrefurl=http://www.vectortemplates.com/raster-batman.php&docid=FxbVmggVf--0dM&imgurl=http://www.vectortemplates.com/raster/batman-logo-big.gif&w=2072&h=1225&ei=Zeo_UoSWIMaR0AXl_YHIBg&zoom=1";
//    String imageURL = "https://drive.google.com/file/d/1gQJA2ZF3oX1XbPch2fGvkeQHzgAM4Zpe/view?usp=sharing";
    String url = "https://drive.google.com/file/d/1w2duAz0gVv_Jq1NoHIFtt2GNGAWj_HoC/view?usp=sharing"; // location of our audio's URL if it exists
    static final String audioPlaying = "Pause our audio recording";
    static final String audioPause = "Resume our audio recording";
    static final String audioPlay = "Play our audio recording";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        tvTitle = findViewById(R.id.tvExperienceTitle);
        ivImage = findViewById(R.id.ivExperienceImage);
        btnAudio = findViewById(R.id.btnAudioControl);
        tvAudioDesc = findViewById(R.id.tvAudio);
        tvDescription = findViewById(R.id.tvExperienceTitle);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrevious);
        audioOn = false;

        // find audio file in database


        // audio file found!
        mp = new MediaPlayer();
//        mp = MediaPlayer.create(this, R.raw.tree_stump_marker);

        if (url != "x") {
        }
        else {
            btnAudio.setVisibility(View.INVISIBLE);
            tvAudioDesc.setVisibility(View.INVISIBLE);
        }

        // find image in database

        // put image into image view
//        Picasso.get()
//                .load(imageURL1)
//                .into(ivImage);
        ivImage.setImageResource(R.drawable.treestumpmarker);
    }

    // allow user to move forward to next image/description
    public void nextPushed(View v) {
        Log.d("ExperienceActivity", "Moving forward once image!");
        // get next image info
    }

    // allow user to move backwards to next image/description
    public void prevPushed(View v) {
        Log.d("ExperienceActivity", "Moving back once!");
        // get previous image info
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
//            mp.start();
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
        try {
            mp.setDataSource(getApplicationContext(),
                        Uri.parse("/Users/kimbell/Documents/CSCE_4623/FinalProject/PreserveAR/app/src/main/res/raw/tree_stump_marker.mp3"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioAttributes attrs = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).build();
        mp.setAudioAttributes(attrs);

        new AsyncTask<Void,Void,Boolean>() {
            @Override
            protected Boolean doInBackgr
            ound(Void... voids) {
                try {
                    mp.prepare();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean prepared) {
                if (prepared) {
                    mp.start();
                }
            }
        }.execute();

//        mp = new MediaPlayer();
//        try {
//            mp.setDataSource(getApplicationContext(),
//                    Uri.parse("/Users/kimbell/Documents/CSCE_4623/FinalProject/PreserveAR/app/src/main/res/raw/tree_stump_marker.mp3"));
//
//            if (Build.VERSION.SDK_INT >= 21) {
//                mp.setAudioAttributes(new AudioAttributes.Builder()
//                        .setUsage(AudioAttributes.USAGE_MEDIA)
//                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                        .build());
//            } else {
//                mp.setAudioStreamType(AudioManager.STREAM_ALARM);
//            }
//            mp.prepare();
//            mp.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        mp = MediaPlayer.create(this, R.raw.tree_stump_marker);
//        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                                     @Override
//                                     public void onPrepared(MediaPlayer mediaPlayer) {
//                                         mp.start();
//                                     }
//                                 });
//        mp.prepareAsync();

//        mp = new MediaPlayer();

//        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // get the audio source
//        try {
//            mp.setDataSource(url);
//            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mediaPlayer) {
//                    mp.start();
//                }
//            });
//            mp.prepareAsync();
////            mp.start();
//        } catch (IOException e) {
//            Log.d("ExperienceActivity", "Audio failed to load!");
//        }

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