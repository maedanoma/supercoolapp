package com.goodboy.manoma.coolapp.framework;

import android.app.Activity;
import android.util.Pair;
import android.view.MotionEvent;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * こいつが {@link ScreenViewManager} の実態だ！
 */
class ScreenViewManagerImpl implements ScreenViewManager {
    private final Activity mContext;
    private final Map<Class, Pair<ScreenView, AtomicBoolean>> mScreenViews;
    private ScreenView mCurrentView;

    public ScreenViewManagerImpl(Activity context, Map screenViews) {
        mContext = context;
        mScreenViews = screenViews;
        mCurrentView = null;
    }

    @Override
    public synchronized <T extends ScreenView> void transit(Class<T> viewName, boolean back) {
        if (mCurrentView != null) {
            mCurrentView.onDisappear();
        }

        Pair<ScreenView, AtomicBoolean> pair = mScreenViews.get(viewName);
        ScreenView view = pair.first;
        AtomicBoolean isFirst = pair.second;

        mContext.setContentView(view.getLayoutId());
        mCurrentView = view;
//        if (isFirst.getAndSet(false)) {
//            mCurrentView.onCreate(mContext);
//        }
        mCurrentView.onAppear(mContext);
    }

    @Override
    public void onBackKeyPressed() {
        mCurrentView.onBackKeyPressed();
    }

    @Override
    public void onTouch(MotionEvent event) {
        mCurrentView.onTouch(event);
    }

    @Override
    public void onClickMenuItem(int id) {
        mCurrentView.onClick(id);
    }
}
