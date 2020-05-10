package com.ztx.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: 张天旭
 * @Date: 2020/5/7 14:59
 * @Version 1.0
 */
public class PropertyMgr {

    private static Properties props;

    static {
        try {
            props = new Properties();
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return (String) props.get(key);
    }
}
