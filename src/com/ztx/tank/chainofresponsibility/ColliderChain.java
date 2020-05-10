package com.ztx.tank.chainofresponsibility;

import com.ztx.tank.AbstractGameObject;
import com.ztx.tank.PropertyMgr;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 张天旭
 * @Date: 2020/5/9 16:32
 * @Version 1.0
 */
public class ColliderChain implements Collider {

    /**
     * 这是个容器，存放所有碰撞检测的实现
     */
    private List<Collider> collides;

    public ColliderChain() {
        initColliders();
    }
    // 碰撞器初始化
    private void initColliders() {
        collides = new ArrayList<>();
        String[] collidersNames = PropertyMgr.get("colliders").split(",");
        try {
            for (String name : collidersNames) {
                Class clazz = Class.forName("com.ztx.tank.chainofresponsibility." + name);
                Collider c = (Collider) clazz.getConstructor().newInstance();
                collides.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 初始化完毕后先进行碰撞检测
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        for (Collider collider : collides) {
            if (!collider.collide(go1, go2)) {
                return false;
            }
        }
        return true;
    }
}
