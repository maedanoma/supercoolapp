package com.goodboy.manoma.coolapp.passmap;

import android.app.Activity;
import android.util.Log;

import com.goodboy.manoma.coolapp.R;
import com.goodboy.manoma.coolapp.framework.Controller;
import com.goodboy.manoma.coolapp.framework.ScreenViewManager;
import com.goodboy.manoma.coolapp.framework.ViewEventParam;

import java.util.List;

/**
 * Created by mitsukim on 2019/11/27.
 */
public class NumberSettingController extends Controller<NumberSettingView> {
    private final PassMapService mService;
    public NumberSettingController(Activity activity, NumberSettingView view, ScreenViewManager manager) {
        super(activity, view, manager);
        mService = PassMapService.getInstance();
    }

    @Override
    public void onAppear(ViewEventParam param) {

    }

    @Override
    public void onDisappear(ViewEventParam param) {

    }

    @Override
    public void onBackKeyPressed(ViewEventParam param) {

    }

    @Override
    public void onTouch(ViewEventParam param) {

    }

    @Override
    public void onClick(int id, ViewEventParam param) {
        Log.w("TAG", "here");
        switch (id) {
            case R.id.next_button:
//                String errorMessage = param.get(String.class);
//                if (!errorMessage.isEmpty()) {
//                    // TODO ダイアログ出して終わり
//                    Log.w("TAG", errorMessage);
//                    return;
//                }
                mService.addPlayers(param.get(List.class));
//                mScreenManager.transit(PassSettingView.class, false);
                mScreenManager.transit(PassSettingView.class, false);
        }
    }
}
