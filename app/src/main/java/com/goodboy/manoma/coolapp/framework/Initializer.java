package com.goodboy.manoma.coolapp.framework;

import android.app.Activity;
import android.util.Pair;

/**
 * アプリの初期化をしてくれるぞ！頼りになるな！
 */
public interface Initializer {

    static Initializer newInstance() {
        return new InitializerImpl();
    }

    <V extends ScreenView, C extends Controller<V>> ActivityEventListener initialize(
            Activity activity, Pair<Class<V>, Class<C>> ...vcPairs);
}
