package com.ztx.tank;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 张天旭
 * @Date: 2020/4/29 22:09
 * @Version 1.0
 */

/**
 *  p11
 *  p11--58 单元测试
 */
public class Main {

    public static void main(String[] args) {
        TankFrame.INSTANCE.setVisible(true);

        new Thread(()->new Audio("audio/war1.wav").loop()).start();
        for (; ; ) {
            try {
                TimeUnit.MILLISECONDS.sleep(25);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 相当于调用paint方法，因为paint方法只有系统可以初始化参数
            TankFrame.INSTANCE.repaint();

        }
    }


}
