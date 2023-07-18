# Cardinal NPE Crash Demo

## Getting Started:

1. Update SDK Location in local.properties
2. To Recreate the crash:
   1. Run the 'App' project
   2. You will see a Activity with 'Start SDK activity' Button. Once you click the button, you will be taken the SDK activity (similar to challenge screen).
   3. Put the App in background, with home key.
   4. Once the App is in background, Open logcat in Android Studio and press the Stop key in logcat panel (Red Square button).
   5. Re-open the app from Multitasking pane.
   6. Press 'Return to App' button || CRASH happens here.


There are code comments throughout the project explaining various issues that prevent the SDK to return control back to the App after Android System kills the App while its in background.

## Alternative Re-creation Steps using Android Debug Bridge (ADB)

> NOTE: These steps only apply if Android SDK Platform Tools are installed

1. Update SDK Location in local.properties
2. To Recreate the crash:
   1. Run the 'App' project
   2. You will see a Activity with 'Start SDK activity' Button. Once you click the button, you will be taken the SDK activity (similar to challenge screen).
   3. Put the App in background, with home key.
   4. Open Terminal (or other CLI program) and run `adb shell am kill com.example.myapplication`
   5. Re-open the app from the Multitasking pane.
   6. Press 'Return to App' button || CRASH happens here.

