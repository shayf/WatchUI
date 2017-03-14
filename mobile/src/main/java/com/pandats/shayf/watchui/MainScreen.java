package com.pandats.shayf.watchui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;

public class MainScreen extends Activity {
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Thread Handling
    ////////////////////////////////////////////////////////////////////////////////////////////////
    protected void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("Background Thread");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }
    protected void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Activity Lifecycle
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onResume() {
        super.onResume();
        startBackgroundThread();
    }
    @Override
    protected void onPause() {
        stopBackgroundThread();
        super.onPause();
    }
}
