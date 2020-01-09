package com.goodboy.manoma.coolapp.framework;

import android.view.View;

/**
 * {@link ScreenView} からのイベントを {@link Controller} が受けるためのリスナーだ！
 * 渡すデータがなくても必ず {@link ViewEventFactory} で {@link ViewEventParam} を作って渡してくれ！
 */
public interface ViewEventListener {
    int ON_TOUCH_EVENT = -100; // TODO かり
    int ON_DOUBLE_TAP_EVENT = -200; // TODO かり
    int ON_RELEASE_EVENT = -300; // TODO かり
    void onAppear(ViewEventParam param);
    void onDisappear(ViewEventParam param);
    void onBackKeyPressed(ViewEventParam param);
    void onTouch(ViewEventParam param);
    void onClick(int id, ViewEventParam param);
}
