package com.example.testlibrary;

import android.content.Context;

public interface CallbackService {

    /**
     * Similar to CardinalMobileSDK, this interface allows Mock Library to hand back control to the App.
     * @param currentContext
     * @param result
     */
    void onValidated(Context currentContext, String result);
}
