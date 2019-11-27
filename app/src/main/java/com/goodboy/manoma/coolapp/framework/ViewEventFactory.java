package com.goodboy.manoma.coolapp.framework;

/**
 * {@link ScreenView} がこいつを一つもつ！
 * こいつはその {@link ScreenView} のためのイベントを作成するぞ！
 */
public class ViewEventFactory {
    private final Class mId;

    public <V> ViewEventFactory(Class<V> id) {
        mId = id;
    }

    public ViewEventParam create() {
        return new ViewEventParam(mId);
    }
}
