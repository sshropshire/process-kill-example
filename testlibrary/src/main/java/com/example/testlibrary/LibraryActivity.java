package com.example.testlibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LibraryActivity extends AppCompatActivity {

    public static String EXTRA_MESSAGE = "com.example.testlibrary.EXTRA_MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        Button returnToApp = findViewById(R.id.return_to_app_button);
        returnToApp.setOnClickListener(view -> {
            Intent data = new Intent();
            data.putExtra(LibraryActivity.EXTRA_MESSAGE, "SUCCESS");
            setResult(Activity.RESULT_OK, data);
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("LibraryActivity: " + "onStart() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("LibraryActivity: " + "onRestart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("LibraryActivity: " + "onResume() called");
        if (UIInteractionFactory.getInstance().getCallback() != null) {
            System.out.println("LibraryActivity: " + "onResume(): callback not null");
        } else {
            System.out.println("LibraryActivity: " + "onResume(): callback is null");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("LibraryActivity: " + "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("LibraryActivity: " + "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("LibraryActivity: " + "onDestroy() called");
    }

    @Override
    public void onBackPressed() {
    }
}