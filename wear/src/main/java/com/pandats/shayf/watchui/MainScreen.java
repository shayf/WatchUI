package com.pandats.shayf.watchui;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.wearable.activity.WearableActivity;


public class MainScreen extends WearableActivity {
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        setAmbientEnabled();
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {

        } else {

        }
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
