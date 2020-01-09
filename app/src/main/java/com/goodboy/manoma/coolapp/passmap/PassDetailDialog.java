package com.goodboy.manoma.coolapp.passmap;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.goodboy.manoma.coolapp.R;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by mitsukim on 2019/12/08.
 */
public class PassDetailDialog extends Dialog {
    private static final List<Pair<Integer, Integer>> sLayoutIds;
    private static final List<Integer> sAllNumbers;
    private static final int NUMBER_SIZE = 100;
    static {
        sLayoutIds = new LinkedList<>();
        sLayoutIds.add(Pair.create(R.id.to_number1, R.id.number_of_pass1));
        sLayoutIds.add(Pair.create(R.id.to_number2, R.id.number_of_pass2));
        sLayoutIds.add(Pair.create(R.id.to_number3, R.id.number_of_pass3));
        sLayoutIds.add(Pair.create(R.id.to_number4, R.id.number_of_pass4));
        sLayoutIds.add(Pair.create(R.id.to_number5, R.id.number_of_pass5));
        sLayoutIds.add(Pair.create(R.id.to_number6, R.id.number_of_pass6));
        sLayoutIds.add(Pair.create(R.id.to_number7, R.id.number_of_pass7));
        sLayoutIds.add(Pair.create(R.id.to_number8, R.id.number_of_pass8));
        sLayoutIds.add(Pair.create(R.id.to_number9, R.id.number_of_pass9));
        sLayoutIds.add(Pair.create(R.id.to_number10, R.id.number_of_pass10));
        sLayoutIds.add(Pair.create(R.id.to_number11, R.id.number_of_pass11));

        sAllNumbers = new LinkedList<>();
        for (int i = 0; i < NUMBER_SIZE; i++) {
            sAllNumbers.add(i);
        }
    }

    private final Activity mContext;
    private final View.OnClickListener mListener;
    private final List<Pair<TextView, Spinner>> mWidgets;
    private int mCurrentNumber;

    PassDetailDialog(@NonNull Context context, View.OnClickListener listener) {
        super(context);
        setContentView(R.layout.layout_pass_detail_setting);
        mContext = (Activity) context;
        mListener = listener;
        mWidgets = new LinkedList<>();
    }

    private void init(Activity context, int toNumber, int toPass) {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(
                context, android.R.layout.simple_spinner_item, sAllNumbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(toPass);
        spinner.setAdapter(adapter);
        mWidgets.add(Pair.create((TextView)findViewById(toNumber), spinner));
    }

    void onCreateDialog(int number) {
        mWidgets.clear();
        mCurrentNumber = number;
        for (Pair<Integer, Integer> pair : sLayoutIds) {
            init(mContext, pair.first, pair.second);
        }
        findViewById(R.id.set_ok_button).setOnClickListener(mListener);
    }

    Map getPassDetail() {
        Map<Integer, Integer> passDetail = new HashMap();
        for (Pair<TextView, Spinner> pair : mWidgets) {
            int number = Integer.parseInt(pair.first.getText().toString());
            int numberOfPass = Integer.parseInt(pair.second.getSelectedItem().toString());
            passDetail.put(number, numberOfPass);
        }
        return passDetail;
    }

    int getCurrentNumber() {
        return mCurrentNumber;
    }

    /**
     * TODO 引数リファクタリング map
     * @param passDetail パス先の背番号とパスの数
     */
    void update(List<Pair<Integer, Integer>> passDetail) {
        for (int i = 0; i < passDetail.size(); i++) {
            TextView number = mWidgets.get(i).first;
            int num = passDetail.get(i).first;
            number.setText(String.valueOf(num));
            number.setEnabled(mCurrentNumber != num);
            Spinner numberOfPass = mWidgets.get(i).second;
            numberOfPass.setSelection(passDetail.get(i).second);
            numberOfPass.setEnabled(mCurrentNumber != num);
        }
    }
}
