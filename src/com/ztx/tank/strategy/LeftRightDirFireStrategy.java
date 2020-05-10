package com.ztx.tank.strategy;

import com.ztx.tank.*;

/**
 * @Author: 张天旭
 * @Date: 2020/5/7 15:48
 * @Version 1.0
 */
public class LeftRightDirFireStrategy implements FireStrategy {

    @Override
    public void fire(Player p) {
        int bx = p.getX() + ResourceMgr.goodTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int by = p.getY() + ResourceMgr.goodTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;

        Dir[] dirs = Dir.values();
        TankFrame.INSTANCE.getGm().add(new Bullet(bx, by, Dir.L, p.getGroup()));
        TankFrame.INSTANCE.getGm().add(new Bullet(bx, by, Dir.R, p.getGroup()));

    }
}
