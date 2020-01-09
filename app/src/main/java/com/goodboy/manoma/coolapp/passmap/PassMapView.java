package com.goodboy.manoma.coolapp.passmap;

import android.app.Activity;
import android.graphics.Canvas;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.MotionEvent;
import android.widget.ActionMenuView;
import android.widget.Button;

import com.goodboy.manoma.coolapp.R;
import com.goodboy.manoma.coolapp.entity.Pass;
import com.goodboy.manoma.coolapp.entity.Player;
import com.goodboy.manoma.coolapp.framework.ScreenView;

import java.util.List;

/**
 * Created by mitsukim on 2019/11/28.
 */

public class PassMapView extends ScreenView {
    private Canvas mCanvas;
    public PassMapView(Activity context, Class clazz) {
        super(context, clazz, R.layout.dammy);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
        setBackgroundResource(R.drawable.field);
        mListener.onAppear(mEventFactory.create());
    }

    void displayPassMap(List<Player> players, List<Pass> passes) {
        // まずは線を引く
        for (Pass pass : passes) {
            pass.draw(mCanvas);
        }

        for (Player player : players) {
            player.draw(mCanvas);
        }
    }

    @Override
    public void onAppear(Activity activity) {
        // 図形を描画するために。
        ActionMenuItemView item = findViewById(activity, R.id.next_button, ActionMenuItemView.class);
        item.setVisibility(GONE);
        activity.setContentView(this);
    }

    @Override
    public void onDisappear() {
    }

    @Override
    public void onBackKeyPressed() {
        mListener.onBackKeyPressed(mEventFactory.create());
    }

    @Override
    public void onTouch(MotionEvent event) {
    }

    @Override
    protected void onClick(int buttonId) {
    }
}
