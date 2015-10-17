package com.example.sakshiudeshi.myapplication;

import android.graphics.Color;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by sakshiudeshi on 10/16/15.
 */
public class GestureTap extends GestureDetector.SimpleOnGestureListener {
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.i("onSingleTap :", "" + e.getAction());
        return true;
    }
}
