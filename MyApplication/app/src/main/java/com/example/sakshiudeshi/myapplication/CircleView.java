package com.example.sakshiudeshi.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;

import java.util.Random;

/**
 * Created by sakshiudeshi on 10/16/15.
 */
public class CircleView extends View {
    private final Paint drawPaint;
    private float size;

    public CircleView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        drawPaint = new Paint();

        drawPaint.setAntiAlias(true);
        setOnMeasureCallback();
    }

    public static String getRandomColor() {
        int max = 255;
        int min = 0;
        Random rand = new Random();
        int red = rand.nextInt((max - min) + 1) + min;
        int green = rand.nextInt((max - min) + 1) + min;
        int blue = rand.nextInt((max - min) + 1) + min;
        return String.format("#%02x%02x%02x", red, green, blue);
    }

    public int getRandomRadius() {
        int max = 300;
        int min = 50;
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public int getRandomLayoutParams(int limit) {
        int max = 750;
        int min = limit;
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;

    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        drawPaint.setColor(Color.parseColor(getRandomColor()));
        int radius = getRandomRadius();
        int layoutSize = getRandomLayoutParams(radius);
        canvas.drawCircle(size, size, radius, drawPaint);

    }

    public static double getArmX(double length, double angle) {
        return Math.cos(angle) * length;
    }

    public static double getArmY(double length, double angle) {
        return Math.sin(angle) * length;
    }

    private void setOnMeasureCallback() {
        ViewTreeObserver vto = getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                removeOnGlobalLayoutListener(this);
                size = getMeasuredWidth() / 2;
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void removeOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (Build.VERSION.SDK_INT < 16) {
            getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        } else {
            getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        }
    }

}
