package edu.uark.kacounts.preservear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PreserveActivity extends AppCompatActivity {
    Button takePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preserve);
        getSupportActionBar().setTitle("Preserve AR");
        takePhoto = findViewById(R.id.btnTakePhoto);
    }

    // call up camera and then swap over to Preserve Description Activity
    public void startPreserveDescriptionActivity(View v) {
        Log.d("PreserveActivity", "Going to take photo!");
        Intent switchActivityIntent = new Intent(this, PreserveDescriptionActivity.class);
        startActivity(switchActivityIntent);
        //Remove itself from activity
        finish();
    }

}