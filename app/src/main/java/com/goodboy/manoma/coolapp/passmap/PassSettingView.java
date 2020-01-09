package com.goodboy.manoma.coolapp.passmap;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.goodboy.manoma.coolapp.R;
import com.goodboy.manoma.coolapp.entity.Player;
import com.goodboy.manoma.coolapp.entity.Position;
import com.goodboy.manoma.coolapp.framework.ScreenView;
import com.goodboy.manoma.coolapp.framework.ViewEventParam;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.goodboy.manoma.coolapp.framework.ViewEventListener.ON_DOUBLE_TAP_EVENT;
import static com.goodboy.manoma.coolapp.framework.ViewEventListener.ON_RELEASE_EVENT;
import static com.goodboy.manoma.coolapp.framework.ViewEventListener.ON_TOUCH_EVENT;

/**
 * Created by mitsukim on 2019/11/28.
 */
public class PassSettingView extends ScreenView {
    private static final String TAG = "PassSetView";
    private static final List<Integer> mLayoutIds;
    static {
        mLayoutIds = new LinkedList<>();
        mLayoutIds.add(R.id.icon1);
        mLayoutIds.add(R.id.icon2);
        mLayoutIds.add(R.id.icon3);
        mLayoutIds.add(R.id.icon4);
        mLayoutIds.add(R.id.icon5);
        mLayoutIds.add(R.id.icon6);
        mLayoutIds.add(R.id.icon7);
        mLayoutIds.add(R.id.icon8);
        mLayoutIds.add(R.id.icon9);
        mLayoutIds.add(R.id.icon10);
        mLayoutIds.add(R.id.icon11);
    }
    private final List<ImageView> mIcons;
    private final Map<Integer, Integer> mIconToNumber;
    private final IconEventListener mIconEventListener;

    public PassSettingView(Activity context, Class clazz) {
        super(context, clazz, R.layout.layout_pass_setting);
        mIcons = new LinkedList<>();
        mIconToNumber = new HashMap<>();
        mIconEventListener = new IconEventListener();
    }

    @Override
    public void onAppear(Activity context) {
        for (Integer layoutId : mLayoutIds) {
            ImageView imageView = findViewById(context, layoutId, ImageView.class);
            imageView.setOnTouchListener(mIconEventListener);
            mIcons.add(imageView);
        }
        mListener.onAppear(mEventFactory.create());
    }

    void updateNumbers(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            int number = players.get(i).getNumber();
            mIconToNumber.put(mLayoutIds.get(i), number);
            displayIcon(mIcons.get(i), players.get(i).getPosition(), players.get(i));
        }
    }

    @Override
    public void onDisappear() {
        mIcons.clear();
        mIconToNumber.clear();
    }

    @Override
    public void onBackKeyPressed() {
        mListener.onBackKeyPressed(mEventFactory.create());
    }

    @Override
    public void onTouch(MotionEvent event) {
    }

    public void displayIcon(Position position, Player player) {
        displayIcon(mIconEventListener.mCurrentSelectedIcon, position, player);
    }

    public void displayIcon(ImageView view, Position position, Player player) {
        int x = position.getX();
        int y = position.getY();
        int size = 60;
        view.setImageBitmap(player.createDefaultBitmap());
        view.layout(x, y, x + size, y + size);
        Log.i("TAG", "icon: x = " + x + ", y = " + y + ", size = " + size);
    }

    @Override
    protected void onClick(int buttonId) {
        switch (buttonId) {
            case R.id.next_button:
                mListener.onClick(buttonId, mEventFactory.create());
                break;
            default:
                break;
        }
    }

    private class IconEventListener extends AbsIconEventListener {
        ImageView mCurrentSelectedIcon;

        @Override
        void onDrag(View v, int x, int y) {
            v.performClick();
            mCurrentSelectedIcon = (ImageView) v;
            int dx = mCurrentSelectedIcon.getLeft() + (x - mPreX);
            int dy = mCurrentSelectedIcon.getTop() + (y - mPreY);
            int number = mIconToNumber.get(v.getId());
            ViewEventParam param = mEventFactory.create()
                    .put(Integer.class, number)
                    .put(Position.class, new Position(dx, dy));
            mListener.onClick(ON_TOUCH_EVENT, param);
        }

        @Override
        void onRelease(View v) {
            mCurrentSelectedIcon = (ImageView) v;
            int number = mIconToNumber.get(v.getId());
            int x = mCurrentSelectedIcon.getLeft();
            int y = mCurrentSelectedIcon.getTop();
            ViewEventParam param = mEventFactory.create()
                    .put(Integer.class, number)
                    .put(Position.class, new Position(x + 30,  y + 30));
            mListener.onClick(ON_RELEASE_EVENT, param);
        }

        @Override
        void onDoubleTap(View v) {
            int num = mIconToNumber.get(v.getId());
            ViewEventParam param = mEventFactory.create().put(Integer.class, num);
            mListener.onClick(ON_DOUBLE_TAP_EVENT, param);
        }
    }
}
