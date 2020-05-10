package com.ztx.tank.chainofresponsibility;

import com.ztx.tank.AbstractGameObject;
import com.ztx.tank.Bullet;
import com.ztx.tank.Tank;
import com.ztx.tank.Wall;

/**
 * @Author: 张天旭
 * @Date: 2020/5/8 11:41
 * @Version 1.0
 */
public class TankWallCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Tank && go2 instanceof Wall) {

//            System.out.println(go1);
//            System.out.println(go2);

            Tank t = (Tank) go1;
            Wall w = (Wall) go2;
            if (t.isLive()) {
                if (t.getRect().intersects(w.getRect())) {
                    t.back();
                }
            }
        } else if (go1 instanceof Wall && go2 instanceof Bullet) {
            collide(go2, go1);
        }
        return true;
    }
}
