package com.ztx.tank.chainofresponsibility;

import com.ztx.tank.AbstractGameObject;
import com.ztx.tank.Bullet;
import com.ztx.tank.ResourceMgr;
import com.ztx.tank.Tank;

import java.awt.*;

/**
 * @Author: 张天旭
 * @Date: 2020/5/8 11:30
 * @Version 1.0
 */
public class BulletTankCollider implements Collider {

    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Bullet && go2 instanceof Tank) {
            Bullet b = (Bullet) go1;
            Tank t = (Tank) go2;
            // 判断这个坦克是否已经被消灭，如果已经被消灭就不在进行碰撞判断
            if (!t.isLive()) {
                return false;
            }
            // 判断子弹是不是已经死掉了（避免两个坦克在一个位置而造成一个子弹打死两个坦克）
            if (!b.isLive()) {
                return false;
            }
            // 判断子弹的发射放是不是一个阵营的，如果是，就不相撞了
            if (b.getGroup() == t.getGroup()) {
                return false;
            }
//        Rectangle rect = new Rectangle(x, y, ResourceMgr.bulletU.getWidth(), ResourceMgr.bulletU.getHeight());
            Rectangle rectTank = t.getRect();

            // 判断子弹和坦克是不是相交
            if (b.getRect().intersects(rectTank)) {
                b.die();
                t.die();
                return false;
            }
        } else if (go2 instanceof Bullet && go1 instanceof Tank) {
            return collide(go2, go1);
        }
        return true;
    }
}
