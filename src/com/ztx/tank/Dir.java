package com.ztx.tank;

import java.util.Random;

/**
 * @Author: 张天旭
 * @Date: 2020/5/6 11:47
 * @Version 1.0
 */
public enum Dir {
    /**
     * 左，上，右，下
     */
    L, U, R, D;

    private static Random r = new Random();

    public static Dir randomDir() {
        return values()[r.nextInt(values().length)];
    }
}
