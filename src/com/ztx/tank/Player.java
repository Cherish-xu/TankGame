package com.ztx.tank;

import com.ztx.tank.strategy.DefaultFireStrategy;
import com.ztx.tank.strategy.FireStrategy;
import com.ztx.tank.strategy.FourDirFireStrategy;
import com.ztx.tank.strategy.LeftRightDirFireStrategy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;

/**
 * @Author: 张天旭
 * @Date: 2020/5/6 11:27
 * @Version 1.0
 */
public class Player implements Serializable {

    public static final int SPEED = 5;
    private int x, y;
    private Dir dir;
    private boolean bl, br, bu, bd;
    private boolean moving = false;
    private Group group;
    private boolean live = true;

    public Player(int x, int y, Dir dir, Group group) {
        this.group = group;
        this.x = x;
        this.dir = dir;
        this.y = y;
        // 初始化开火策略
        this.initFireStrategy();
    }

    public static int getSPEED() {
        return SPEED;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    // 将自己画出来，坦克
    public void paint(Graphics g) {
        // 先进性坦克是否被消灭的判断，如果被消灭了，就不在画出来了
        if (!this.isLive()) {
            return;
        }
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.goodTankL, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.goodTankR, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.goodTankU, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.goodTankD, x, y, null);
                break;
            default:
        }

        move();
    }

    // 按下事件
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bl = true;
                break;
            case KeyEvent.VK_RIGHT:
                br = true;
                break;
            case KeyEvent.VK_UP:
                bu = true;
                break;
            case KeyEvent.VK_DOWN:
                bd = true;
                break;
            default:
        }
        setMainDir();

    }

    // 判断上下左右都没有被按下
    // 朝哪个方向走
    private void setMainDir() {
        // 如果所有的按键都抬起来了，坦克应该停止
        if (!bl && !br && !bu && !bd) {
            moving = false;
        }else {
            // 如果有按键按下，则表明坦克在运动
            moving = true;

            if (bl && !br && !bu && !bd) {
                dir = Dir.L;
            }
            if (!bl && br && !bu && !bd) {
                dir = Dir.R;
            }
            if (!bl && !br && bu && !bd) {
                dir = Dir.U;
            }
            if (!bl && !br && !bu && bd) {
                dir = Dir.D;
            }
        }
    }

    // 负责移动
    private void move() {
        if (!moving) {
            return;
        }
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
    }

    // 弹起事件
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bl = false;
                break;
            case KeyEvent.VK_RIGHT:
                br = false;
                break;
            case KeyEvent.VK_UP:
                bu = false;
                break;
            case KeyEvent.VK_DOWN:
                bd = false;
                break;
            case KeyEvent.VK_CONTROL:
                fire();
                break;
            default:
        }
        setMainDir();
    }

    private FireStrategy strategy = null;
    private void initFireStrategy() {
        try {
            String className = PropertyMgr.get("tankFireStrategy");
            Class clazz = Class.forName("com.ztx.tank.strategy."+className);
            // 先拿到这个类的构造方法，在new一个对象
            strategy = (FireStrategy) clazz.getDeclaredConstructor().newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //抬起Ctrl键发射子弹
    private void fire() {
        strategy.fire(this);
    }

    // 当子弹与坦克相撞，坦克死掉
    public void die() {
        this.setLive(false);
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

    public boolean isBl() {
        return bl;
    }

    public void setBl(boolean bl) {
        this.bl = bl;
    }

    public boolean isBr() {
        return br;
    }

    public void setBr(boolean br) {
        this.br = br;
    }

    public boolean isBu() {
        return bu;
    }

    public void setBu(boolean bu) {
        this.bu = bu;
    }

    public boolean isBd() {
        return bd;
    }

    public void setBd(boolean bd) {
        this.bd = bd;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
