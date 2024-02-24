package com.example.moodjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public abstract class MoodHistoryPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history_page);

    }
}