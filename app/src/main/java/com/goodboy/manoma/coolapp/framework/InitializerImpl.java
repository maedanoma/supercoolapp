package com.goodboy.manoma.coolapp.framework;

import android.app.Activity;
import android.util.Log;
import android.util.Pair;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

class InitializerImpl implements Initializer {
    @Override
    public <V extends ScreenView, C extends Controller<V>> ActivityEventListener initialize(
            Activity activity, Pair<Class<V>, Class<C>> ...vcPairs) {
        Map screenViews = new HashMap();
        ScreenViewManager manager = ScreenViewManager.newInstance(activity, screenViews);
        for (Pair<Class<V>, Class<C>> pair : vcPairs) {
            contract(pair.first, pair.second, activity, manager, screenViews);
        }
        // 一番上のViewを最初に表示する
        manager.transit(vcPairs[0].first, false);
        return manager;
    }

    private <V extends ScreenView, C extends Controller> void contract(
            Class<V> vClass, Class<C> cClass, Activity activity, ScreenViewManager manager,
            Map<Class<V>, Pair<ScreenView, AtomicBoolean>> screenViews) {
        Class<?>[] viewParamTypes = {Activity.class, Class.class};
        Object[] viewValues = {activity, cClass};
        V view = newInstance(vClass, viewParamTypes, viewValues);

        Class<?>[] controllerParamTypes = {Activity.class, vClass, ScreenViewManager.class};
        Object[] controllerValues = {activity, view, manager};
        Controller controller = newInstance(cClass, controllerParamTypes, controllerValues);

        ViewEventDispatcher.getInstance().put(cClass, controller);

        screenViews.put(vClass, Pair.create(view, new AtomicBoolean(true)));
    }

    private <S> S newInstance(Class<S> rClass, Class<?>[] types, Object[] args)
            throws ExceptionInInitializerError{
        if (types.length != args.length) {
            throw new ExceptionInInitializerError("constructor params length is not equals.");
        }
        try {
            return rClass.getConstructor(types).newInstance(args);
        } catch (InstantiationException | IllegalAccessException |
                InvocationTargetException | NoSuchMethodException e) {
            Log.e("TAG", "error occurred.",e);
            throw new ExceptionInInitializerError(e);
        }
    }
}
