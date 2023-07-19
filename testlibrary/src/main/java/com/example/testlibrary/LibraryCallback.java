package com.example.testlibrary;

public interface LibraryCallback {

    /**
     * Similar to CardinalMobileSDK, this interface allows Mock Library to hand back control to the App.
     * @param message
     */
    void onValidated(String message);
}
