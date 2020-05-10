package com.ztx.tank.strategy;

import com.ztx.tank.Player;

import java.io.Serializable;

/**
 * @Author: 张天旭
 * @Date: 2020/5/7 15:32
 * @Version 1.0
 */
public interface FireStrategy extends Serializable {
    public void fire(Player p);
}
