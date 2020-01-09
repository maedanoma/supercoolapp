package com.goodboy.manoma.coolapp.framework;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * 画面を作る時はこいつを継承してくれ！
 */
public abstract class ScreenView extends View implements View.OnClickListener {
    protected final ViewEventDispatcher mListener;
    protected final ViewEventFactory mEventFactory;
    private final int mLayoutId;
    /**
     * 子クラスのコンストラクタにlayoutId入れないでくれ！！
     */
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
        if (clazz.equals(Button.class)) {
            v.setOnClickListener(this);
        }
        return v;
    }

    public abstract void onAppear(Activity activity);

    public abstract void onDisappear();

    public abstract void onBackKeyPressed();

    public abstract void onTouch(MotionEvent event);

    protected abstract void onClick(int buttonId);

    @Override
    public void onClick(View v) {
        onClick(v.getId());
    }

}
