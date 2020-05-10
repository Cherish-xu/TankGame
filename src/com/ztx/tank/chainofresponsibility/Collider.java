package com.ztx.tank.chainofresponsibility;

import com.ztx.tank.AbstractGameObject;

import java.io.Serializable;

/**
 * @Author: 张天旭
 * @Date: 2020/5/8 11:29
 * @Version 1.0
 */
public interface Collider extends Serializable {
    /**
     * 判断是否继续进行判断
     * @param go1 游戏物体1
     * @param go2 游戏物体2
     * @return 返回true继续，返回false停止
     */
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2);

}
