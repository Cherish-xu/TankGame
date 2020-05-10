package com.ztx.tank;

import com.ztx.tank.chainofresponsibility.ColliderChain;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 张天旭
 * @Date: 2020/5/10 10:21
 * @Version 1.0
 */
public class GameModel implements Serializable {

    // 玩家坦克
    private Player myTank;
    // 碰撞检测责任链
    ColliderChain chain = new ColliderChain();
    // 装游戏物体的集合
    List<AbstractGameObject> objects;

    public GameModel() {
        // 初始化游戏，将主战坦克，敌军坦克，子弹初始化
        initGameObjects();
    }

    // 初始化游戏物体，坦克，子弹等
    private void initGameObjects() {
        objects = new ArrayList<>();

        // 主战坦克
        myTank = new Player(100, 100, Dir.R, Group.GOOD);
        // 从配置文件读取初始的敌军坦克数量
        int tankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));

        for (int i = 0; i < tankCount; i++) {
            this.add(new Tank(100 + 150 * i, 400, Dir.D, Group.BAD));
        }

        // 添加一堵墙
        this.add(new Wall(300, 100, 400, 10));
    }

    // 添加游戏物体
    public void add(AbstractGameObject go) {
        objects.add(go);
    }

    // 画处所需物体
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullets:" + objects.size(), 10, 50);
        g.setColor(c);

        // 参数代表的是系统提供的一个画笔
        // 画一个方框  横坐标    纵坐标      宽度        高度
        // 将画笔交给坦克自己，让他自己管理
        myTank.paint(g);

        for (int i = 0; i < objects.size(); i++) {
            AbstractGameObject object = objects.get(i);
            if (!object.isLive()) {
                objects.remove(object);
                break;
            }
        }

        for (int i = 0; i < objects.size(); i++) {

            AbstractGameObject go1 = objects.get(i);
            for (int j = 0; j < objects.size(); j++) {
                AbstractGameObject go2 = objects.get(j);
                chain.collide(go1, go2);
            }
            if (objects.get(i).isLive()) {
                objects.get(i).paint(g);
            }
        }
    }

    public Player getMyTank() {
        return myTank;
    }
}
