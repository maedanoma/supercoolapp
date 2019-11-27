package com.goodboy.manoma.coolapp.framework;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * 画面を作る時はこいつを継承してくれ！
 */
public abstract class ScreenView extends View implements View.OnClickListener {
    protected final ViewEventDispatcher mListener;
    protected final ViewEventFactory mEventFactory;
    private final int mLayoutId;
    public ScreenView(Activity context, Class clazz, int layoutId) {
        super(context);
        mEventFactory = new ViewEventFactory(clazz);
        mListener = ViewEventDispatcher.getInstance();
        mLayoutId = layoutId;
    }

    final int getLayoutId() {
        return mLayoutId;
    }

    protected <V extends View> V findViewById(Activity context, int layoutId, Class<V> clazz) {
        V v = (V) context.findViewById(layoutId);
        if (v == null) {
            throw new IllegalStateException("view is null");
        }
        return v;
    }

    public abstract void onCreate(Activity context);

    public abstract void onAppear();

    public abstract void onDisappear();

    public abstract void onBackKeyPressed();

    protected abstract void onClick(int buttonId);

    @Override
    public void onClick(View v) {
        onClick(v.getId());
    }

}
