package com.ztx.tank;

import java.awt.*;

/**
 * @Author: 张天旭
 * @Date: 2020/5/7 16:25
 * @Version 1.0
 */
public class Wall extends AbstractGameObject{
    private int x, y, w, h;
    private Rectangle rect;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        rect = new Rectangle(x, y, w, h);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(x, y, w, h);
        g.setColor(c);

        // 测试墙的位置
//        Color old = g.getColor();
//        g.setColor(Color.red);
//        g.drawRect(rect.x, rect.y, rect.width, rect.height);
//        g.setColor(old);

    }

    @Override
    public boolean isLive() {
        return true;
    }

    @Override
    public String toString() {
        return "Wall{" +
                "x=" + x +
                ", y=" + y +
                ", w=" + w +
                ", h=" + h +
                ", rect=" + rect +
                '}';
    }

    public Rectangle getRect() {
        return rect;
    }
}
