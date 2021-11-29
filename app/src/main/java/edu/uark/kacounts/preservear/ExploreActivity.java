package edu.uark.kacounts.preservear;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ExploreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        getSupportActionBar().setTitle("Explore AR");
    }
}