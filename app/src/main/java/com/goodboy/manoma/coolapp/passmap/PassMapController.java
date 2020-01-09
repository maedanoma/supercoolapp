package com.goodboy.manoma.coolapp.passmap;

import android.app.Activity;

import com.goodboy.manoma.coolapp.framework.Controller;
import com.goodboy.manoma.coolapp.framework.ScreenViewManager;
import com.goodboy.manoma.coolapp.framework.ViewEventParam;

/**
 * Created by mitsukim on 2019/11/28.
 */

public class PassMapController extends Controller<PassMapView> {
    private final PassMapService mService;
    public PassMapController(Activity activity, PassMapView view, ScreenViewManager manager) {
        super(activity, view, manager);
        mService = PassMapService.getInstance();
    }

    @Override
    public void onAppear(ViewEventParam param) {
        mView.displayPassMap(
                mService.getPlayers(), mService.getAllPasses());
    }

    @Override
    public void onDisappear(ViewEventParam param) {

    }

    @Override
    public void onBackKeyPressed(ViewEventParam param) {
        mScreenManager.transit(PassSettingView.class, false);
    }

    @Override
    public void onTouch(ViewEventParam param) {

    }

    @Override
    public void onClick(int id, ViewEventParam param) {

    }
}
