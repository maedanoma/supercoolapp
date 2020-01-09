package com.goodboy.manoma.coolapp.entity;

import android.graphics.Color;

/**
 * Created by mitsukim on 2019/11/30.
 */

public class IconColor {
    final int mIconColor;
    final int mNumberColor;

    public IconColor(int iconColor, int numberColor) {
        mIconColor = iconColor;
        mNumberColor = numberColor;
    }

    public IconColor() {
        mIconColor = Color.BLUE;
        mNumberColor = Color.WHITE;
    }
}
