package com.goodboy.manoma.coolapp.passmap;

import android.app.Activity;
import android.util.Log;

import com.goodboy.manoma.coolapp.R;
import com.goodboy.manoma.coolapp.entity.Pass;
import com.goodboy.manoma.coolapp.entity.Player;
import com.goodboy.manoma.coolapp.entity.Position;
import com.goodboy.manoma.coolapp.framework.Controller;
import com.goodboy.manoma.coolapp.framework.ScreenViewManager;
import com.goodboy.manoma.coolapp.framework.ViewEventParam;

import java.util.List;

/**
 * Created by mitsukim on 2019/11/28.
 */
public class PassSettingController extends Controller<PassSettingView> {
    private static final String TAG = "PassSetCtrl";
    private final PassMapService mService;
    private final PassDetailDialog mDialog;
    // TODO あとで
//    private final Map<Integer, ViewEventDispatcher.FunctionInterface> mIdToFunc;
    public PassSettingController(Activity activity, PassSettingView view, ScreenViewManager manager) {
        super(activity, view, manager);
        mService = PassMapService.getInstance();
        mDialog = new PassDetailDialog(activity,
                v -> PassSettingController.this.onClick(v.getId(), null));
//        mIdToFunc = new HashMap<>();
//        mIdToFunc.put(R.id.set_ok_button, this::onClickDialogOk);
    }

    @Override
    public void onAppear(ViewEventParam param) {
        mView.updateNumbers(mService.getPlayers());
    }

    @Override
    public void onDisappear(ViewEventParam param) {

    }

    @Override
    public void onBackKeyPressed(ViewEventParam param) {
        mScreenManager.transit(NumberSettingView.class, false);
    }

    @Override
    public void onTouch(ViewEventParam param) {

    }

    @Override
    public void onClick(int id, ViewEventParam param) {
        switch (id) {
            case R.id.next_button:
                mScreenManager.transit(PassMapView.class, false);
                return;
            case R.id.set_ok_button:
                onClickDialogOk(param);
                return;
            case ON_TOUCH_EVENT:
                onDragIcon(param);
                return;
            case ON_RELEASE_EVENT:
                onReleaseIcon(param);
                return;
            case ON_DOUBLE_TAP_EVENT:
                onDoubleClickIcon(param);
                return;
            default:
                break;
        }
    }

    private void onClickDialogOk(ViewEventParam param) {
        Log.i(TAG, "onClickDialog.");
        mDialog.dismiss();
        mService.setPassDetail(mDialog.getCurrentNumber(), mDialog.getPassDetail());
    }

    private void onDragIcon(ViewEventParam param) {
        Position position = param.get(Position.class);
        Player player = mService.getPlayer(param.get(Integer.class));
        mView.displayIcon(position, player);
    }

    private void onReleaseIcon(ViewEventParam param) {
        Log.i(TAG, "onReleaseIcon.");
        int number = param.get(Integer.class);
        Position pos = param.get(Position.class);
        mService.setPosition(number, pos);
    }

    private void onDoubleClickIcon(ViewEventParam param) {
        Log.i(TAG, "onDoubleClickIcon.");
        int number = param.get(Integer.class);
        mDialog.onCreateDialog(number);
        mDialog.update(mService.getPassDetail(number));
        mDialog.show();
    }
}
