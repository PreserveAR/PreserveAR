package edu.uark.kacounts.preservear;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< Updated upstream
import android.os.Bundle;

public class ExploreActivity extends AppCompatActivity {

=======
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import edu.uark.kacounts.preservear.Data.DefaultDataGenerator;
import edu.uark.kacounts.preservear.Data.DefaultDataGeneratorCallback;
import edu.uark.kacounts.preservear.Data.Photo;
import edu.uark.kacounts.preservear.Data.PhotoDataSource;
import edu.uark.kacounts.preservear.Data.PhotoRepository;
import edu.uark.kacounts.preservear.MapsActivity.MapsActivity;
import util.AppExecutors;

public class ExploreActivity extends AppCompatActivity {

    PhotoRepository experiences;
    private List<Photo> photosList;
    Button btnToMap;
    RecyclerView rvExperiences;

<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        getSupportActionBar().setTitle("Explore AR");
<<<<<<< Updated upstream
=======
        btnToMap = findViewById(R.id.btnToMap);
        rvExperiences = findViewById(R.id.rvExperienceList);

        photosList = new ArrayList<>();

        // set up repository
        experiences = Injection.providePhotoRepository(new AppExecutors(), getApplicationContext());

        // set up default data
        checkFirstRun();
    }

    // take user to map activity
    public void startMap(View v) {
        Log.d("ExploreActivity", "Going to open up map!");
        Intent switchActivityIntent = new Intent(this, MapsActivity.class);
        startActivity(switchActivityIntent);
        Intent mapsIntent = new Intent();
        mapsIntent.setClass(this, MapsActivity.class);
        startActivity(mapsIntent);

>>>>>>> Stashed changes
    }

    public void generateDefaultData() {
        DefaultDataGenerator defaultDataGenerator = new DefaultDataGenerator();
        defaultDataGenerator.generateDefaultData(new DefaultDataGeneratorCallback() {
            @Override
            public void defaultDataCreated(List<Photo> photos) {
                for (int i = 0; i < photos.size(); i++) {
                    experiences.createPhoto(photos.get(i), new PhotoDataSource.CreatePhotoCallback() {
                        @Override
                        public void onPhotoCreated(int id) {
                            return;
                        }

                        @Override
                        public void onPhotoCreateFail() {
                            return;
                        }
                    });
                }
                getPhotosFromRepository();
            }

            @Override
            public void onDataNotCreated() {

            }
        });
    }

    private void checkFirstRun() {
        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {

            // This is just a normal run
            return;

        } else if (savedVersionCode == DOESNT_EXIST) {
            generateDefaultData();
            prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();

            // TODO This is a new install (or the user cleared the shared preferences)

        } else if (currentVersionCode > savedVersionCode) {
            generateDefaultData();
            prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();

            // TODO This is an upgrade
        }
    }

    public void getPhotosFromRepository(){
        experiences.getPhotos(new PhotoDataSource.LoadPhotosCallback() {
            @Override
            public void onPhotosLoaded(List<Photo> photos) {
                photosList = photos;
                notifyPhotosLoaded();
            }

            @Override
            public void onDataNotAvailable() {
                Log.e("InboxPresenter","Data not loaded");
            }
        });
    }

<<<<<<< Updated upstream
=======
    public void generateDefaultData() {
        DefaultDataGenerator defaultDataGenerator = new DefaultDataGenerator();
        defaultDataGenerator.generateDefaultData(new DefaultDataGeneratorCallback() {
            @Override
            public void defaultDataCreated(List<Photo> photos) {
                for (int i = 0; i < photos.size(); i++) {
                    experiences.createPhoto(photos.get(i), new PhotoDataSource.CreatePhotoCallback() {
                        @Override
                        public void onPhotoCreated(int id) {
                            return;
                        }

                        @Override
                        public void onPhotoCreateFail() {
                            return;
                        }
                    });
                }
                getPhotosFromRepository();
            }

            @Override
            public void onDataNotCreated() {

            }
        });
    }

    private void checkFirstRun() {
        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {

            // This is just a normal run
            return;

        } else if (savedVersionCode == DOESNT_EXIST) {
            generateDefaultData();
            prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();

            // TODO This is a new install (or the user cleared the shared preferences)

        } else if (currentVersionCode > savedVersionCode) {
            generateDefaultData();
            prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();

            // TODO This is an upgrade
        }
    }

    public void getPhotosFromRepository(){
        experiences.getPhotos(new PhotoDataSource.LoadPhotosCallback() {
            @Override
            public void onPhotosLoaded(List<Photo> photos) {
                photosList = photos;
                notifyPhotosLoaded();
            }

            @Override
            public void onDataNotAvailable() {
                Log.e("InboxPresenter","Data not loaded");
            }
        });
    }

>>>>>>> Stashed changes
    public void notifyPhotosLoaded() {
        ((ExploreAdapter)rvExperiences.getAdapter()).setLocalDataSet(photosList);
        rvExperiences.getAdapter().notifyDataSetChanged();
    }

//    public List<Photo> getPhotos() {
//        return photosList;
//    }
}