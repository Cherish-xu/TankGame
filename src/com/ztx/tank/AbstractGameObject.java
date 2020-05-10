package com.ztx.tank;

import java.awt.*;
import java.io.Serializable;

/**
 * @Author: 张天旭
 * @Date: 2020/5/7 16:36
 * @Version 1.0
 */
public abstract class AbstractGameObject implements Serializable {
    /**
     * 将游戏中的元素画出来
     * @param g
     */
    public abstract void paint(Graphics g);

    public abstract boolean isLive();
}