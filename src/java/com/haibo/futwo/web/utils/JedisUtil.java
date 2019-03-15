package com.haibo.futwo.web.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author:haibo.xiong
 * @date:2018/12/3
 * @description:
 */
@Component
public class JedisUtil {
    @Resource
    private JedisPool jedisPool;

    private Jedis getResource() {
        Jedis jedis = jedisPool.getResource();
        return jedisPool.getResource();
    }

    /**
     * 设置key，value
     *
     * @param key
     * @param value
     * @return
     */
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = getResource();
        try {
            jedis.set(key, value);
            return value;
        } finally {
            jedis.close();
        }
    }

    /**
     * 设置key，value
     *
     * @param key
     * @param value
     * @return
     */
    public String sets(String key, String value) {
        Jedis jedis = getResource();
        try {
            jedis.set(key, value,"NX","EX",120);
            return value;
        } finally {
            jedis.close();
        }
    }

    /**
     * 设置key，value
     *
     * @param key
     * @param value
     * @return
     */
    public Object seto(String key, Object value) {
        Jedis jedis = getResource();
        JSONObject jb = new JSONObject();
        jb.put("value", value);
        try {
            jedis.set(key, jb.toJSONString(),"NX","EX",120);
            return value;
        } finally {
            jedis.close();
        }
    }

    /**
     *
     * 获取指定 key 的值
     *
     * @param key
     * @return T value
     */
    public <T> T geto(String key, Class<T> clazz) {
        Jedis jedis = getResource();
        String str = null;
        try {
            str = jedis.get(key);
        } finally {
            jedis.close();
        }
        if (!StringUtils.isEmpty(str)) {
            JSONObject jb = JSONObject.parseObject(str);
            if (jb == null) {
                // TODO 删 开始
                if(key != null && key.indexOf("/ebkroom/index") != -1){
                    System.out.println("getLintTest:jb is null.");
                }
                // TODO 删 结束
                return null;
            } else if (jb.get("value") == null) {
                // TODO 删 开始
                if(key != null && key.indexOf("/ebkroom/index") != -1){
                    System.out.println("getLintTest:jb.get(value) == null.");
                }
                // TODO 删 结束
                return null;
            }
            T t = JSONObject.parseObject(jb.get("value").toString(), clazz);
            return t;
        } else {
            // TODO 删 开始
            if(key != null && key.indexOf("/ebkroom/index") != -1){
                System.out.println("getLintTest:get link from redis by this.get(key) is empty.");
            }
            // TODO 删 结束
            return null;
        }
    }

    /**
     * 设置超时时间
     *
     * @param key
     * @param i
     */
    public void expire(byte[] key, int i) {
        Jedis jedis = getResource();
        try {
            jedis.expire(key, i);
        } finally {
            jedis.close();
        }
    }

    /**
     * 获取key的value
     *
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        Jedis jedis = getResource();
        try {
            byte[] value = jedis.get(key);
            return value;
        } finally {
            jedis.close();
        }
    }

    /**
     * 获取key的value
     *
     * @param key
     * @return
     */
    public String gets(String key) {
        Jedis jedis = getResource();
        try {
            String value = jedis.get(key);
            return value;
        } catch (Exception e) {
            jedis.quit();
            jedis.disconnect();
            return "";
        } finally {
            jedis.close();
        }
    }

    public void del(byte[] key) {
        Jedis jedis = getResource();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public Set<byte[]> keys(String shiro_session_prefix) {
        Jedis jedis = getResource();
        try {
            return jedis.keys((shiro_session_prefix + "*").getBytes());
        } finally {
            jedis.close();
        }
    }
}
