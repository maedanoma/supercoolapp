package com.goodboy.manoma.coolapp.framework;

import android.view.View;

/**
 * {@link ScreenView} からのイベントを {@link Controller} が受けるためのリスナーだ！
 */
public interface ViewEventListener {
    void onAppear(ViewEventParam param);
    void onDisappear(ViewEventParam param);
    void onBackKeyPressed(ViewEventParam param);
    void onClick(int id, ViewEventParam param);
}
