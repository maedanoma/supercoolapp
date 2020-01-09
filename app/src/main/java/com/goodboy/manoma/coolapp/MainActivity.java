package com.goodboy.manoma.coolapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.goodboy.manoma.coolapp.framework.ActivityEventListener;
import com.goodboy.manoma.coolapp.framework.Initializer;
import com.goodboy.manoma.coolapp.passmap.NumberSettingController;
import com.goodboy.manoma.coolapp.passmap.NumberSettingView;
import com.goodboy.manoma.coolapp.passmap.PassMapController;
import com.goodboy.manoma.coolapp.passmap.PassMapView;
import com.goodboy.manoma.coolapp.passmap.PassSettingController;
import com.goodboy.manoma.coolapp.passmap.PassSettingView;

public class MainActivity extends AppCompatActivity {
    private ActivityEventListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initializer initializer = Initializer.newInstance();
        Pair[] pairs = {
                Pair.create(NumberSettingView.class, NumberSettingController.class),
                Pair.create(PassSettingView.class, PassSettingController.class),
                Pair.create(PassMapView.class, PassMapController.class)
        };
        mListener = initializer.initialize(this, pairs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = super.onOptionsItemSelected(item);
        mListener.onClickMenuItem(item.getItemId());
        return result;
    }

    @Override
    public void onBackPressed() {
        mListener.onBackKeyPressed();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result =  super.onTouchEvent(event);
        mListener.onTouch(event);
        return result;
    }
}
