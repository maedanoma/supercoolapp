package com.goodboy.manoma.coolapp.framework;

import android.app.Activity;

import java.util.Map;

/**
 * 画面遷移をするときはこのクラスを使うんだ！
 */
public interface ScreenViewManager extends ActivityEventListener {

    static ScreenViewManager newInstance(Activity context, Map screenViews) {
        return new ScreenViewManagerImpl(context, screenViews);
    }
    <T extends ScreenView> void transit(Class<T> views, boolean back);
}
