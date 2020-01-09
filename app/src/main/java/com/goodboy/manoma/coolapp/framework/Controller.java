package com.goodboy.manoma.coolapp.framework;

import android.app.Activity;

//import com.goodboy.manoma.coolapp.utils.DialogFactoryImpl;

/**
 * {@link ViewEventListener} を使って {@link ScreenView} からのイベントと
 * モデルの橋渡しをするコントローラーだ！大事に使ってくれよな！
 * 継承するときジェネリクスに対象Viewを設定してくれよな！
 */
public abstract class Controller <V extends ScreenView> implements ViewEventListener {
    protected final V mView;
    protected final ScreenViewManager mScreenManager;
//    protected final DialogFactoryImpl mDialogFactory;

    /**
     * 子クラスのコンストラクタはpublicにするのだ！
     */
    protected Controller(Activity activity, V view, ScreenViewManager manager) {
//        mDialogFactory = new DialogFactoryImpl(activity);
        mView = view;
        mScreenManager = manager;
    }
}
