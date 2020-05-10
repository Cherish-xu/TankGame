package com.ztx.tank;

import java.awt.*;

/**
 * @Author: 张天旭
 * @Date: 2020/5/6 17:46
 * @Version 1.0
 */
public class Bullet extends AbstractGameObject {

    public static final int SPEED = 10;
    private int x, y;
    private Dir dir;
    private Group group;
    private boolean live = true;
    private int w = ResourceMgr.bulletU.getWidth();
    private int h = ResourceMgr.bulletU.getHeight();

    private Rectangle rect;


    public Bullet(int x, int y, Dir dir, Group group) {
        this.group = group;
        this.x = x;
        this.y = y;
        this.dir = dir;

        rect = new Rectangle(x, y, w, h);
    }

    @Override
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    @Override
    public void paint(Graphics g) {

        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            default:
        }

        move();

        rect.x = x;
        rect.y = y;

        // 测试子弹的位置
//        Color old = g.getColor();
//        g.setColor(Color.red);
//        g.drawRect(rect.x, rect.y, rect.width, rect.height);
//        g.setColor(old);
    }

    //子弹移动的方法
    private void move() {
        switch (dir) {
            case L:
                x -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case D:
                y += SPEED;
                break;
            default:
        }
        // 子弹边界检查
        boundsCheck();
    }

    public Rectangle getRect() {
        return rect;
    }

    // 子弹和坦克相撞，子弹死掉
    public void die() {
        this.setLive(false);
    }

    // 边界检查，超出边界的子弹回收
    private void boundsCheck() {
        if (x < 0 || y < 30 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            live = false;
        }
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", group=" + group +
                ", live=" + live +
                ", w=" + w +
                ", h=" + h +
                ", rect=" + rect +
                '}';
    }














    public static int getSPEED() {
        return SPEED;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
}
