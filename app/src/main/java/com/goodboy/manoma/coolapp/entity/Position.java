package com.goodboy.manoma.coolapp.entity;

/**
 * Created by mitsukim on 2019/11/30.
 */
public class Position {
    final int x;
    final int y;

    public Position(int xpos, int ypos) {
        x = xpos;
        y = ypos;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
