package com.ztx.tank;

import java.awt.*;

/**
 * @Author: 张天旭
 * @Date: 2020/5/6 17:46
 * @Version 1.0
 */
public class Explode extends AbstractGameObject{

    private int x, y;
    private int width, height;
    private int step = 0;

    private boolean live = true;

    @Override
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;

        this.width = ResourceMgr.explodes[0].getWidth();
        this.height = ResourceMgr.explodes[0].getHeight();
    }


    @Override
    public void paint(Graphics g) {
        if (!live) {
            return;
        }

        g.drawImage(ResourceMgr.explodes[step], x, y, null);
        step++;
        if (step >= ResourceMgr.explodes.length) {
            this.die();
        }

    }

    private void die() {
        this.live = false;
    }

}
