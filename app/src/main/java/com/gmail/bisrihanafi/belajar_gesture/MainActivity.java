package com.gmail.bisrihanafi.belajar_gesture;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    TextView gestureText;
    private GestureDetectorCompat gDetector;
    RelativeLayout ly;
    public final int SWIPE_THRESHOLD=100;
    public final int SWIPE_V_THRESHOLD=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureText=(TextView)findViewById(R.id.text1);
        ly=(RelativeLayout)findViewById(R.id.activity_main);
        this.gDetector = new GestureDetectorCompat(this, this);
        gDetector.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        gestureText.setText ("Single Tap Confirmed");
        ly.setBackgroundColor(Color.BLUE);
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        gestureText.setText ("Double tap");
        ly.setBackgroundColor(Color.DKGRAY);
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        gestureText.setText ("Double tap Event");
        ly.setBackgroundColor(Color.WHITE);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        ly.setBackgroundColor(Color.YELLOW);
        gestureText.setText ("onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        gestureText.setText ("Show Press");
        ly.setBackgroundColor(Color.DKGRAY);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        gestureText.setText ("Single Tap Up");
        ly.setBackgroundColor(Color.LTGRAY);
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        gestureText.setText ("On Scroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        gestureText.setText ("Long Press");
        Intent i = new Intent(MainActivity.this,MultiTouch.class);
        startActivity(i);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        gestureText.setText ("Fling");
        float dify=e2.getY()-e1.getY();
        float difx=e2.getX()-e1.getX();
        if (Math.abs(difx)> Math.abs(dify)) {
            if (Math.abs(difx) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_V_THRESHOLD) {
                if (difx>0){
                    ly.setBackgroundColor(Color.RED);
                }else{
                    ly.setBackgroundColor(Color.WHITE);
                }

            }
        }else{
            if (Math.abs(dify) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_V_THRESHOLD) {
                if (dify>0){
                    ly.setBackgroundColor(Color.GREEN);
                }else{
                    ly.setBackgroundColor(Color.WHITE);
                }

            }
        }
        return true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
