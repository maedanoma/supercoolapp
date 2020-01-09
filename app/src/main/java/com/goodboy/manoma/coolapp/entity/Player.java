package com.goodboy.manoma.coolapp.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mitsukim on 2019/11/30.
 */

public class Player {
    public enum Size {
        XL(70, 20), L(60, 15), M(50, 10), S(40, 5), XS(30, 0);

        final int mSize;
        final int mNumberOfPass;

        Size(int size, int numberOfPass) {
            mSize = size;
            mNumberOfPass = numberOfPass;
        }

        public static Size from(int numberOfPass) {
            for (Size size : Size.values()) {
                if (numberOfPass >= size.mNumberOfPass) {
                    return size;
                }
            }
            return XS;
        }

        int toInt() {
            return mSize;
        }
    }

    private static final Position DEFAULT_POSITION = new Position(15, 15);
    private static final int BITMAP_RADIUS = 30;
    private static final int BITMAP_SIZE = BITMAP_RADIUS * 2;

    private final int mNumber;
    private final IconColor mColors;
    private final List<Pass> mPasses;
    private Position mPosition;

    public Player(int number, IconColor colors) {
        mNumber = number;
        mColors = colors;
        mPasses = new ArrayList<>();
        mPosition = DEFAULT_POSITION;
    }

    public int getNumber() {
        return mNumber;
    }

    public int getNumberOfPassTo(Player player) {
        for (Pass pass : mPasses) {
            if (pass.getReceiverNumber() == player.getNumber()) {
                return pass.getNumberOfPass();
            }
        }
        return 0;
    }

    public void setPosition(Position position) {
        mPosition = position;
    }

    public Position getPosition() {
        return mPosition;
    }

    private int getNumberOfPass() {
        int numberOfPass = 0;
        for (Pass pass : mPasses) {
            numberOfPass += pass.getNumberOfPass();
        }
        return numberOfPass;
    }

    public void draw(Canvas canvas) {
        int size = Size.from(getNumberOfPass()).toInt();
        // 円を描画
        Paint circlePaint = new Paint();
        circlePaint.setColor(mColors.mIconColor);
        circlePaint.setAlpha(255);
        circlePaint.setAntiAlias(true);
        canvas.drawCircle(mPosition.x, mPosition.y, size, circlePaint);
        // 文字を描画
        Paint textPaint = new Paint();
        textPaint.setColor(mColors.mNumberColor);
        textPaint.setTextSize(size);
        textPaint.setAlpha(255);
        int x = mPosition.x - (mNumber >= 10 ? 15 : 10);
        canvas.drawText(String.valueOf(mNumber), x, mPosition.y + 10, textPaint);
    }

    public Bitmap createDefaultBitmap() {
        Bitmap bmp = Bitmap.createBitmap(
                BITMAP_SIZE, BITMAP_SIZE, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        // 円を描画
        Paint circlePaint = new Paint();
        circlePaint.setColor(mColors.mIconColor);
        circlePaint.setAlpha(255);
        circlePaint.setAntiAlias(true);
//        canvas.drawCircle(size.toInt(), size.toInt(), size.toInt(), circlePaint);
        canvas.drawCircle(BITMAP_RADIUS, BITMAP_RADIUS, BITMAP_RADIUS, circlePaint);
        // 文字を描画
        Paint textPaint = new Paint();
        textPaint.setColor(mColors.mNumberColor);
        textPaint.setTextSize(BITMAP_RADIUS);
        textPaint.setAlpha(255);
        int x = BITMAP_RADIUS - (mNumber >= 10 ? 15 : 10);
        canvas.drawText(String.valueOf(mNumber), x, BITMAP_RADIUS + 10, textPaint);
        return bmp;
    }

    public List<Pass> getPasses() {
        return mPasses;
    }

    public void updatePass(Pass pass) {
        Pass currentPass = null;
        for (Pass p : mPasses) {
            if (p.isSamePassReceiver(pass)) {
                currentPass = p;
                break;
            }
        }
        if (currentPass != null) {
            mPasses.remove(currentPass);
        }
        mPasses.add(pass);
        Log.i("TAG", "update pass = " + pass.getNumberOfPass());
        Log.i("TAG", "number of pass = " + getNumberOfPass());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        Player player = (Player) obj;
        return player.getNumber() == mNumber;
    }
}
