package com.ztx.tank.chainofresponsibility;

import com.ztx.tank.AbstractGameObject;
import com.ztx.tank.Bullet;
import com.ztx.tank.Tank;
import com.ztx.tank.Wall;

import java.util.Arrays;

/**
 * @Author: 张天旭
 * @Date: 2020/5/8 11:41
 * @Version 1.0
 */
public class TankTankCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 != go2 && go1 instanceof Tank && go2 instanceof Tank) {

//            System.out.println(go1);
//            System.out.println(go2);

            Tank t1 = (Tank) go1;
            Tank t2 = (Tank) go2;
            if (t1.isLive() && t2.isLive()) {
                if (t1.getRect().intersects(t2.getRect())) {
                    t1.back();
                    t2.back();
                }
            }
        } else if (go1 instanceof Wall && go2 instanceof Bullet) {
            return collide(go2, go1);
        }
        return true;
    }
}
