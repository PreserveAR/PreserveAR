package edu.uark.kacounts.preservear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.uark.kacounts.preservear.MapsActivity.MapsActivity;

public class ExploreActivity extends AppCompatActivity {

    Button btnToMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        getSupportActionBar().setTitle("Explore AR");
        btnToMap = findViewById(R.id.btnToMap);
    }

    // take user to map activity
    public void startMap(View v) {
        Log.d("ExploreActivity", "Going to open up map!");
        Intent switchActivityIntent = new Intent(this, MapsActivity.class);
        startActivity(switchActivityIntent);
        Intent mapsIntent = new Intent();
        mapsIntent.setClass(this, MapsActivity.class);
        startActivity(mapsIntent);

    }
}