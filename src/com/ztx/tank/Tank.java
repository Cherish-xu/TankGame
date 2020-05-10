package com.ztx.tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @Author: 张天旭
 * @Date: 2020/5/6 11:27
 * @Version 1.0
 */
public class Tank extends AbstractGameObject {

    public static final int SPEED = 5;
    private int x, y;
    private Dir dir;
    private boolean bl, br, bu, bd;
    private boolean moving = true;
    private Group group;
    private boolean live = true;
    private Random random = new Random();
    private int oldx, oldy;
    private Rectangle rect;
    // 坦克的宽度和高度
    private int width,height;

    public Tank(int x, int y, Dir dir, Group group) {
        this.group = group;
        this.x = x;
        this.dir = dir;
        this.y = y;
        oldx = x;
        oldy = y;

        this.width = ResourceMgr.goodTankU.getWidth();
        this.height = ResourceMgr.goodTankU.getHeight();

        this.rect = new Rectangle(x, y, width, height);

    }

    public static int getSPEED() {
        return SPEED;
    }

    @Override
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    // 将自己画出来，坦克
    @Override
    public void paint(Graphics g) {
        // 先进性坦克是否被消灭的判断，如果被消灭了，就不在画出来了
        if (!this.isLive()) {
            return;
        }
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.badTankL, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.badTankR, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.badTankU, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.badTankD, x, y, null);
                break;
            default:
        }
        move();

        // 移动过后更新坦克的位置
        rect.x = x;
        rect.y = y;

    }


    // 负责移动
    private void move() {
        if (!moving) {
            return;
        }
        // 判断边界
        oldx = x;
        oldy = y;

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

        // 边界检查
        boundsCheck();
        
        // 随机一个方向运动
        randomDir();
        if (r.nextInt(100) > 90) {
            fire();
        }
    }

    private Random r = new Random();

    // 随机换一个方向运动
    private void randomDir() {
        if (r.nextInt(100) > 90) {

            this.dir = Dir.randomDir();
        }
    }

    // 边界检测，不能把坦克开出边界
    private void boundsCheck() {
        if (x < 0 || y < 0 || x + width > TankFrame.GAME_WIDTH || y + height > TankFrame.GAME_HEIGHT) {
            // 如果坦克超出边界了，就让他回到上一帧的位置
            this.back();
        }
    }

    // 让坦克回到上一帧的位置
    public void back() {
        this.x = oldx;
        this.y = oldy;
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
    }

    //抬起Ctrl键发射子弹
    private void fire() {
        int bx = x + ResourceMgr.goodTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int by = y + ResourceMgr.goodTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        TankFrame.INSTANCE.getGm().add(new Bullet(bx, by, dir, group));

    }

    // 当子弹与坦克相撞，坦克死掉
    public void die() {
        this.setLive(false);
        TankFrame.INSTANCE.getGm().add(new Explode(x, y));
    }

    public Rectangle getRect() {
        return rect;
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

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

}
