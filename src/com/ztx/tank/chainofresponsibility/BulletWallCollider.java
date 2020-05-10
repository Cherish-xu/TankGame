package com.ztx.tank.chainofresponsibility;

import com.ztx.tank.AbstractGameObject;
import com.ztx.tank.Bullet;
import com.ztx.tank.Wall;

/**
 * @Author: 张天旭
 * @Date: 2020/5/8 11:41
 * @Version 1.0
 */
public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Bullet && go2 instanceof Wall) {

//            System.out.println(go1);
//            System.out.println(go2);

            Bullet b = (Bullet) go1;
            Wall w = (Wall) go2;
            if (b.isLive()) {
                if (b.getRect().intersects(w.getRect())) {
                    b.die();
                    return false;
                }
            }
        } else if (go1 instanceof Wall && go2 instanceof Bullet) {
            return collide(go2, go1);
        }
        return true;
    }
}
