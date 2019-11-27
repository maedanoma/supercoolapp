package com.goodboy.manoma.coolapp.framework;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link }
 */

public class ViewEventDispatcher implements ViewEventListener {
    private static final ViewEventDispatcher INSTANCE = new ViewEventDispatcher();
    private final Map<Class, ViewEventListener> mListeners;

    private ViewEventDispatcher() {
        mListeners = new HashMap<>();
    }

    static ViewEventDispatcher getInstance() {
        return INSTANCE;
    }

    void put(Class clazz, ViewEventListener listener) {
        mListeners.put(clazz, listener);
    }

    @Override
    public void onAppear(ViewEventParam param) {
        mListeners.get(param.getControllerClass()).onAppear(param);
    }

    @Override
    public void onDisappear(ViewEventParam param) {
        mListeners.get(param.getControllerClass()).onDisappear(param);
    }

    @Override
    public void onBackKeyPressed(ViewEventParam param) {
        mListeners.get(param.getControllerClass()).onBackKeyPressed(param);
    }

    @Override
    public void onClick(int id, ViewEventParam param) {
        mListeners.get(param.getControllerClass()).onClick(id, param);
    }
}
