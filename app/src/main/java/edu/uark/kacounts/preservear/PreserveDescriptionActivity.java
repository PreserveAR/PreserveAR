package edu.uark.kacounts.preservear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PreserveDescriptionActivity extends AppCompatActivity {
    Button btnSavePhoto;
    EditText title;
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preserve_description);
        getSupportActionBar().setTitle("User Pics of AR");
        btnSavePhoto = findViewById(R.id.btnSavePhoto);
        title = findViewById(R.id.etTitle);
        description = findViewById(R.id.etMessage);
    }

    // save photo uploads user's photo to database and then hides itself and prevents editing
    public void savePhoto(View v) {
        Log.d("PreserveActivity", "User done editing!");
        // upload to our database!

        // hide button
        btnSavePhoto.setVisibility(View.INVISIBLE);
        // make editing stop
        title.setEnabled(false);
        description.setEnabled(false);
    }
}