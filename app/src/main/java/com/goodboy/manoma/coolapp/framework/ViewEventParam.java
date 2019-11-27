package com.goodboy.manoma.coolapp.framework;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link ScreenView} からイベントを {@link Controller} に
 * 通知するときのイベントだ！
 */
public class ViewEventParam {
    private final Class mControllerClass;
    private final Map mMap;

    public ViewEventParam(Class id) {
        mControllerClass = id;
        mMap = new HashMap();
    }

    Class getControllerClass() {
        return mControllerClass;
    }

    public <V> ViewEventParam put(Class<V> clazz, V v) {
        mMap.put(clazz, v);
        return this;
    }

    public <V> V get(Class<V> clazz) {
        return (V) mMap.get(clazz);
    }
}
