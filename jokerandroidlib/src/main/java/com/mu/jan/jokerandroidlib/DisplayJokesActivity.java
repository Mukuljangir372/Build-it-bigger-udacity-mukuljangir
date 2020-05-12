package com.mu.jan.jokerandroidlib;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayJokesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_jokes);

        TextView text = findViewById(R.id.textView_joke);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.activity_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //get joke using intent
        Intent intent = getIntent();
        if(intent.hasExtra("joke")){
            String joke = intent.getStringExtra("joke");
            text.setText(joke);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
