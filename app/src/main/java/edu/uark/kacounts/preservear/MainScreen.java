package edu.uark.kacounts.preservear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {

    Button btnExplore;
    Button btnPreserve;
    Button btnRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getSupportActionBar().setTitle("Welcome to PreserveAR!");
        btnExplore = findViewById(R.id.btnExplore);
        btnPreserve = findViewById(R.id.btnPreserve);
        btnRecycle = findViewById(R.id.btnRecycle);
    }

    // swap over to Explore Activity
    public void startExploreActivity(View v) {
        Intent switchActivityIntent = new Intent(this, ExploreActivity.class);
        startActivity(switchActivityIntent);
    }

    // swap over to Preserve Activity
    public void startPreserveActivity(View v) {
        Intent switchActivityIntent = new Intent(this, PreserveActivity.class);
        startActivity(switchActivityIntent);
    }

    // swap over to Recycle Activity
    public void startRecycleActivity (View v) {
        Intent switchActivityIntent = new Intent(this, RecycleActivity.class);
        startActivity(switchActivityIntent);
    }
}