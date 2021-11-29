package edu.uark.kacounts.preservear;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PreserveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preserve);
        getSupportActionBar().setTitle("Preserve AR");
    }
}