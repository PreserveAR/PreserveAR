package edu.uark.kacounts.preservear;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import edu.uark.kacounts.preservear.Data.DefaultDataGenerator;
import edu.uark.kacounts.preservear.Data.DefaultDataGeneratorCallback;
import edu.uark.kacounts.preservear.Data.Photo;
import edu.uark.kacounts.preservear.Data.PhotoDataSource;
import edu.uark.kacounts.preservear.Data.PhotoRepository;
import util.AppExecutors;

public class LoadUpActivity extends AppCompatActivity {
//    PhotoRepository experiences;
//    private List<Photo> photosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

//        photosList = new ArrayList<>();
//
//        // set up repository
//        experiences = Injection.providePhotoRepository(new AppExecutors(), getApplicationContext());
//
//        // set up default data
//        checkFirstRun();

        // make activity auto end after loading
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 2 seconds
                    sleep(2*1000);

                    // After 3 seconds redirect to another intent
                    Intent i=new Intent(getBaseContext(),MainScreen.class);
                    startActivity(i);

                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();
    }

//    public void generateDefaultData() {
//        DefaultDataGenerator defaultDataGenerator = new DefaultDataGenerator();
//        defaultDataGenerator.generateDefaultData(new DefaultDataGeneratorCallback() {
//            @Override
//            public void defaultDataCreated(List<Photo> photos) {
//                for (int i = 0; i < photos.size(); i++) {
//                    experiences.createPhoto(photos.get(), new PhotoDataSource.CreatePhotoCallback() {
//                        @Override
//                        public void onPhotoCreated(int id) {
//                            return;
//                        }
//
//                        @Override
//                        public void onPhotoCreateFail() {
//                            return;
//                        }
//                    });
//                }
//                getPhotosFromRepository();
//            }
//
//            @Override
//            public void onDataNotCreated() {
//
//            }
//        });
//    }
//
//    private void checkFirstRun() {
//        final String PREFS_NAME = "MyPrefsFile";
//        final String PREF_VERSION_CODE_KEY = "version_code";
//        final int DOESNT_EXIST = -1;
//
//        // Get current version code
//        int currentVersionCode = BuildConfig.VERSION_CODE;
//
//        // Get saved version code
//        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);
//
//        // Check for first run or upgrade
//        if (currentVersionCode == savedVersionCode) {
//
//            // This is just a normal run
//            return;
//
//        } else if (savedVersionCode == DOESNT_EXIST) {
//            generateDefaultData();
//            prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
//
//            // TODO This is a new install (or the user cleared the shared preferences)
//
//        } else if (currentVersionCode > savedVersionCode) {
//            generateDefaultData();
//            prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
//
//            // TODO This is an upgrade
//        }
//    }
//
//    public void getPhotosFromRepository(){
//        experiences.getPhotos(new PhotoDataSource.LoadPhotosCallback() {
//            @Override
//            public void onPhotosLoaded(List<Photo> photos) {
//                photosList = photos;
//                notifyMessagesLoaded();
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                Log.e("InboxPresenter","Data not loaded");
//            }
//        });
//    }
//
//    public void notifyMessagesLoaded() {
//        ((ExploreAdapter)rvInbox.getAdapter()).setLocalDataSet(mPresenter.getMessages());
//        rvInbox.getAdapter().notifyDataSetChanged();
//    }
}