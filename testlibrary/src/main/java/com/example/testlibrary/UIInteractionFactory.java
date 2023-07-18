package com.example.testlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class UIInteractionFactory {

    private static UIInteractionFactory instance;
    private CallbackService callback;

    public static UIInteractionFactory getInstance() {
        if (instance == null) {
            instance = new UIInteractionFactory();
        }
        return instance;
    }

    public CallbackService getCallback() {
        return this.callback;
    }

    /**
     * @param activity        -- reference to app's activity passed in similar to cca_processor()
     * @param callbackService -- similar to CardinalValidateReceiver interface in cca_processor()
     *
     * When Android system kill's the App while its in background, system will not call any Activity LifeCycle method and before crashing.
     * Which makes it difficult to predict and prepare for such an instance. When this happens, the SDK no longer
     * has a non-null reference to the callback to return control over the app (because it is killed by the system).
     *
     * By Default, when android kills the App while its in background, it will try its best to recreate the last activity,
     * in our case its the SDK's Challenge Screen, but it does not do a well enough job to retain its references like callback interface.
     */
    public void startMockChallenge(Activity activity, CallbackService callbackService) {
        this.callback = callbackService;
        Intent intent = new Intent(activity, LibraryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }

    /**
     * Here is where we send user response to ACS and hand back control over to the App.
     * In logcat console, you will notice 'callback' is null after crash
     *
     * @param context - return context from current activity
     * @param result  - return result
     */
    public void sendUserResponse(Context context, String result) {
        if (context == null) {
            System.out.println("UIInteractionFactory: " + "NULL CONTEXT");
        }
        if (result == null) {
            System.out.println("UIInteractionFactory: " + "NULL RESULT");
        }
        if (this.callback == null) {
            System.out.println("UIInteractionFactory: " + "NULL CALLBACK");
        }
        this.callback.onValidated(context, result);
    }
}