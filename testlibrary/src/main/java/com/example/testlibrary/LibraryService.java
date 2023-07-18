package com.example.testlibrary;

import android.app.Activity;

/**
 * This Class is Similar to Cardinal.java, it handles communication with the activity through a Factory class.
 */
public class LibraryService {

    public void startMockChallenge(Activity currentActivity, CallbackService callback) {
        UIInteractionFactory.getInstance().startMockChallenge(currentActivity, callback);
    }
}
