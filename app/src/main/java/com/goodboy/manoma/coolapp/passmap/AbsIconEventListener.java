package com.goodboy.manoma.coolapp.passmap;

import android.os.Handler;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import com.goodboy.manoma.coolapp.framework.ViewEventParam;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.goodboy.manoma.coolapp.framework.ViewEventListener.ON_DOUBLE_TAP_EVENT;

/**
 * Created by mitsukim on 2019/12/02.
 */
abstract class AbsIconEventListener implements View.OnTouchListener {
    protected int mPreX;
    protected int mPreY;
    private final AtomicBoolean isDoubleTap = new AtomicBoolean(false);
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                onDrag(v, x, y);
                break;
            case MotionEvent.ACTION_UP:
                onRelease(v);
                if (isDoubleTap.getAndSet(true)) {
                    onDoubleTap(v);
                }
                Handler handler = new Handler();
                handler.postDelayed(() -> isDoubleTap.set(false), 500);
                break;
            default:
                break;
        }
        mPreX = x;
        mPreY = y;
        return true;
    }

    abstract void onDrag(View v, int x, int y);

    abstract void onRelease(View v);

    abstract void onDoubleTap(View v);
}
