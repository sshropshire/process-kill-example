package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testlibrary.LibraryActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    TextView resultTextView = findViewById(R.id.resultTextView);
                    int resultCode = result.getResultCode();

                    String message = null;
                    switch (resultCode) {
                        case Activity.RESULT_OK:
                            Intent intent = result.getData();
                            if (intent != null) {
                                message = intent.getStringExtra(LibraryActivity.EXTRA_MESSAGE);
                            }
                            break;
                        case Activity.RESULT_CANCELED:
                            message = "CANCELED";
                            break;
                        default:
                            message = "UNKNOWN";
                    }

                    String resultText = String.format("Result: %s", message) ;
                    Log.d(TAG, resultText);

                    // also display result on screen
                    resultTextView.setText(resultText);
                }
            });

    /**
     * Before getting started: update SDK location in local.properties.
     *
     * To Recreate NPE issue, start 'app' from Android studio. Once its up and running, click 'Start SDK activity' button.
     * Once the SDK activity is presented. Put the app in background, and press the Stop (red Square button) under 'Logcat' tab in android studio.
     * <p>
     * Once the process is killed, bring the activity back to forefront from multitasking pane. Click 'Return to App' button -- the crash happens here.
     * <p>
     * This demo is created in very simplified manner but still emulates how the merchant app and SDK would behave in this scenario.
     * <p>
     * <p>
     * 1. If you goto -> UIInteractionFactory.java in Library module, we are logging if either context, callback or result are null.
     * In logcat we can verify that callback is null after Android system kills the app.
     * <p>
     * 2. In LibraryActivity we are logging when Android's Lifecycle methods are called, and you can see in logcat that when Android System kills the app,
     * Activity Lifecycle methods are not called. As per normal flow, onPause() and onStop() will be called when we put the App in background.
     * However, onDestroy() is not called when app is killed.
     * <p>
     * 3. We cannot 'save' an instance of Interface in 'savedInstanceState' and retrieve it later. Which limits a lot of the SDK's ability to hand back control
     * after the Android System kills the app while its in background.
     * <p>
     * Typically System will kill apps if its running out of memory and needs to clear apps NOT currently in foreground.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startSDKButton = findViewById(R.id.appButton);
        startSDKButton.setOnClickListener(view -> {
            startLibraryActivity();
        });
    }

    void startLibraryActivity() {
        // clear result text
        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText("");

        Intent intent = new Intent(this, LibraryActivity.class);
        // Ref: https://stackoverflow.com/a/48177487
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activityLauncher.launch(intent);
    }
}