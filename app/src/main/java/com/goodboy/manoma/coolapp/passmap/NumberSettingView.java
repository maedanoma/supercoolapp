package com.goodboy.manoma.coolapp.passmap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Pair;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.goodboy.manoma.coolapp.R;
import com.goodboy.manoma.coolapp.entity.IconColor;
import com.goodboy.manoma.coolapp.entity.Player;
import com.goodboy.manoma.coolapp.framework.ScreenView;
import com.goodboy.manoma.coolapp.framework.ViewEventParam;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mitsukim on 2019/11/27.
 */
public class NumberSettingView extends ScreenView {
    private final List<Spinner> mNumberList;

    private static final List<Pair<Integer, Integer>> sLayoutIds;
    private static final List<Integer> sAllNumbers;
    private static final int NUMBER_SIZE = 100;

    static {
        sLayoutIds = new LinkedList<>();
        sLayoutIds.add(Pair.create(R.id.number1, 0));
        sLayoutIds.add(Pair.create(R.id.number2, 1));
        sLayoutIds.add(Pair.create(R.id.number3, 2));
        sLayoutIds.add(Pair.create(R.id.number4, 3));
        sLayoutIds.add(Pair.create(R.id.number5, 4));
        sLayoutIds.add(Pair.create(R.id.number6, 5));
        sLayoutIds.add(Pair.create(R.id.number7, 6));
        sLayoutIds.add(Pair.create(R.id.number8, 7));
        sLayoutIds.add(Pair.create(R.id.number9, 8));
        sLayoutIds.add(Pair.create(R.id.number10, 9));
        sLayoutIds.add(Pair.create(R.id.number11, 10));

        sAllNumbers = new LinkedList<>();
        for (int i = 1; i < NUMBER_SIZE; i++) {
            sAllNumbers.add(i);
        }
    }
    
    public NumberSettingView(Activity context, Class clazz) {
        super(context, clazz, R.layout.layout_number_setting);
        mNumberList = new LinkedList<>();
    }

    public void onCreate(Activity context) {
        mNumberList.clear();
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(
                context, android.R.layout.simple_spinner_item, sAllNumbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (Pair<Integer, Integer> layoutId : sLayoutIds) {
            Spinner spinner = findViewById(context, layoutId.first, Spinner.class);
            spinner.setAdapter(adapter);
            spinner.setSelection(layoutId.second);
            mNumberList.add(spinner);
        }
    }

    @Override
    public void onAppear(Activity context) {
        onCreate(context);
    }

    @Override
    public void onDisappear() {

    }

    @Override
    public void onBackKeyPressed() {

    }

    @Override
    public void onTouch(MotionEvent event) {

    }

    @Override
    protected void onClick(int buttonId) {
        switch (buttonId) {
            case R.id.next_button:
                IconColor colors = new IconColor(Color.BLUE, Color.WHITE);
                List<Player> players = new LinkedList<>();
                String errorMessage = "";
                int number = -1;
                for (Spinner et : mNumberList) {
                    String num = et.getSelectedItem().toString();
                    if (num.isEmpty()) {
                        // TODO エラーダイアログ
                        errorMessage = "error";
                        break;
                    }
                    try {
                        number = Integer.parseInt(num);
                    } catch (NumberFormatException e) {
                        // TODO エラーダイアログ
                        errorMessage = "error";
                        break;
                    }
                    players.add(new Player(number, colors));
                }
                ViewEventParam param = mEventFactory.create()
//                        .put(String.class, errorMessage) TODO ダイアログを出す責務はコントローラ
                        .put(List.class, players);
                mListener.onClick(buttonId, param);
                break;
            default:
                break;
        }

    }
}
