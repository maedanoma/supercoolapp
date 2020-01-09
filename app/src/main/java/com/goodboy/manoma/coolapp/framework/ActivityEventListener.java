package com.goodboy.manoma.coolapp.framework;

import android.view.MotionEvent;

/**
 * Created by mitsukim on 2019/11/28.
 */

public interface ActivityEventListener {
    void onBackKeyPressed();
    void onTouch(MotionEvent event);
    void onClickMenuItem(int id);
}
