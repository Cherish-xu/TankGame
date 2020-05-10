package com.ztx.tank;

import com.ztx.tank.chainofresponsibility.BulletTankCollider;
import com.ztx.tank.chainofresponsibility.BulletWallCollider;
import com.ztx.tank.chainofresponsibility.Collider;
import com.ztx.tank.chainofresponsibility.ColliderChain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @Author: 张天旭
 * @Date: 2020/4/29 22:09
 * @Version 1.0
 */
public class TankFrame extends Frame {
    public static final TankFrame INSTANCE = new TankFrame();
    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    // 解决图像闪动问题 游戏的双缓冲

    private GameModel gm = new GameModel();
    Image offScreenImage = null;

    private TankFrame() {
        this.setTitle("tank war");
        this.setLocation(400, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);

        // 添加键盘监听器
        this.addKeyListener(new TankKeyListener());
        // 碰撞器进行初始化
//        initColliders();
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);

    }

    @Override
    public void paint(Graphics g) {
        gm.paint(g);
    }

    private class TankKeyListener extends KeyAdapter {
        // 当一个键被按下去的时候
        // 按下事件
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            if (key == KeyEvent.VK_S){
                save();
            } else if (key == KeyEvent.VK_L) {
                load();
            } else {
                // 根据按下的不同的键，让他向不同的方向移动
                // 将监听交给坦克，让其自己监听键盘
                gm.getMyTank().keyPressed(e);
            }

        }
        // 键盘弹起事件
        @Override
        public void keyReleased(KeyEvent e) {
            gm.getMyTank().keyReleased(e);
        }
    }

    // 按下l进行读取
    private void load() {
        ObjectInputStream ois = null;
        try {
            File f = new File("F:/test/tank.dat");
            FileInputStream fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            this.gm = (GameModel) (ois.readObject());


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // 按下s进行存盘
    private void save() {
        ObjectOutputStream oos = null;
        try {
            File f = new File("F:/test/tank.dat");
            FileOutputStream osf = new FileOutputStream(f);
            oos = new ObjectOutputStream(osf);
            oos.writeObject(gm);
            oos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public GameModel getGm() {
        return this.gm;
    }
}
