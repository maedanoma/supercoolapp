package com.goodboy.manoma.coolapp.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by mitsukim on 2019/11/30.
 */

public class Pass {
    public enum Type {
        ZERO(0, 0, -1),
        ONE(1, 4, Color.rgb(124, 252, 0)),
        TWO(2, 8, Color.rgb(173, 255, 47)),
        THREE(3, 12, Color.rgb(255, 255, 0)),
        FOUR(4, 16, Color.rgb(255, 215, 0)),
        FIVE(5, 20, Color.rgb(255, 165, 0)),
        SIX(6, 24, Color.rgb(255, 140, 0)),
        SEVEN(7, 28, Color.rgb(255, 110, 0)),
        EIGHT(8, 32, Color.rgb(255, 80, 0)),
        NINE(9, 36, Color.rgb(255, 40, 0)),
        TEN(10, 40, Color.rgb(255, 0, 0));

        final int mNumberOfPass;
        final int mWidth;
        final int mColor;
        Type(int numberOfPass, int width, int color) {
            mNumberOfPass = numberOfPass;
            mWidth = width;
            mColor = color;
        }

        public static Type from(int numberOfPass) {
            for (Type type : Type.values()) {
                if (numberOfPass == type.mNumberOfPass) {
                    return type;
                }
            }
            return TEN;
        }

        int getWidth() {
            return mWidth;
        }

        int getColor() {
            return mColor;
        }
    }

    private final Player mPasser;
    private final Player mPassReceiver;
    private final int mNumberOfPass;

    public Pass(Player passer, Player passReceiver, int numberOfPass) {
        mPasser = passer;
        mPassReceiver = passReceiver;
        mNumberOfPass = numberOfPass;
    }

    public int getReceiverNumber() {
        return mPassReceiver.getNumber();
    }

    public int getNumberOfPass() {
        return mNumberOfPass;
    }

    public boolean isSamePassRoute(Pass pass) {
        if (mPasser.equals(pass.mPasser)) {
            return mPassReceiver.equals(pass.mPassReceiver);
        }
        if (mPasser.equals(pass.mPassReceiver)) {
            return mPassReceiver.equals(pass.mPasser);
        }
        return false;
    }

    public boolean isSamePassReceiver(Pass pass) {
        return mPassReceiver.equals(pass.mPassReceiver);
    }

    public Pass merge(Pass pass) {
        int numOfPass = mNumberOfPass + pass.mNumberOfPass;
        return new Pass(mPasser, mPassReceiver, numOfPass);
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAlpha(128);
        paint.setColor(Type.from(mNumberOfPass).getColor());
        paint.setStrokeWidth(Type.from(mNumberOfPass).getWidth());
        paint.setAntiAlias(true);
        Position startPos = mPasser.getPosition();
        Position endPos = mPassReceiver.getPosition();
        canvas.drawLine(startPos.x, startPos.y, endPos.x, endPos.y, paint);
        Log.i("TAG", "start : x-> "+ startPos.x + ", y-> " + startPos.y);
        Log.i("TAG", "end : x-> "+ endPos.x + ", y-> " + endPos.y);
    }
}
