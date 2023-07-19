package com.example.testlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.DefaultLifecycleObserver;

/**
 * Slight modification of documentation example found here: https://developer.android.com/training/basics/intents/result#separate
 */
public class LibraryLauncher implements DefaultLifecycleObserver {

    private final LibraryCallback callback;
    private final ActivityResultLauncher<Intent> activityLauncher;

    public LibraryLauncher(@NonNull FragmentActivity activity, @NonNull LibraryCallback callback) {
        activityLauncher = activity.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        handleActivityResult(result);
                    }
                });
        this.callback = callback;
    }

    private void handleActivityResult(ActivityResult result) {
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

        // notify result via callback
        this.callback.onValidated(message);
    }

    public void startLibraryActivity(Context context) {
        Intent intent = new Intent(context, LibraryActivity.class);
        // Ref: https://stackoverflow.com/a/48177487
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activityLauncher.launch(intent);
    }
}
