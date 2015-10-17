package com.example.sakshiudeshi.myapplication;

import android.app.ProgressDialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public String getJSON() throws Exception{
        String url = "http://api.androidhive.info/volley/person_object.json";
        return getHTML(url);

    }

    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "You have pressed a button", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//
//            }
//        });

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Random rand = new Random();
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.screenBrightness = (float) random(0.0, 1.0);
                        getWindow().setAttributes(lp);
                        int max = 255;
                        int min = 0;
                        int red = rand.nextInt((max - min) + 1) + min;
                        int green = rand.nextInt((max - min) + 1) + min;
                        int blue = rand.nextInt((max - min) + 1) + min;
                        int radius = rand.nextInt((300 - 50) + 1) + 50;
                        int startX = rand.nextInt((500 - 0) + 1) + 0;
                        int startY = rand.nextInt((500 - 0) + 1) + 0;
                        int endX = rand.nextInt((500 - 0) + 1) + 0;
                        int endY = rand.nextInt((500 - 0) + 1) + 0;
                        try {
                            Log.i("MyApp", getJSON());
                        } catch (Exception e) {
                            Log.e ("MyApp", "Error " + e.getMessage());
                        }
                        // update UI here
                        getWindow().getDecorView().setBackgroundColor(Color.rgb(blue, green, red));
                        CircleView myCircle = (CircleView) findViewById(R.id.my_circle);
                        myCircle.invalidate();
                        View view = findViewById(R.id.my_circle);
                        showBezier(view, startX, endX, startY, endY);
                                //setCo(Color.rgb(green, red, blue));
//                        getWindow().getDecorView().setBackgroundColor(Color.rgb(255, 0, 0));
                    }
                });
            }
        }, 0, 600);

    }

    private static double random(double min, double max) {
        final double r = Math.random();
        return (r >= 0.5d ? 1.5d - r : r) * (max - min) + min;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void showBezier(View v, int initX, int limitX, int initY, int limitY) {
        ArcTranslateAnimation animation = new ArcTranslateAnimation(
                initX, limitX, initY, limitY);
        animation
                .setInterpolator(new LinearInterpolator());
        animation.setDuration(600);
        animation.setFillAfter(true);

        findViewById(R.id.my_circle).startAnimation(animation);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
