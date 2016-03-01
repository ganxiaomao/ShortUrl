package com.youshusoft.redis;

import org.apache.log4j.Logger;

import com.youshusoft.data.RedisData;
import com.youshusoft.util.Config.RedisConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisAPI {
private static JedisPool pool = null;
private static Logger logger = Logger.getLogger(RedisAPI.class);
    
    /**
     * 构建redis连接池
     * 
     * @param ip
     * @param port
     * @return JedisPool
     */
    public static void init(RedisConfig rc){
            JedisPoolConfig config = new JedisPoolConfig();
            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
//            config.setMaxActive(500);
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(rc.getMaxIdle());
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
//            config.setMaxWait(1000 * 100);
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);
            pool = new JedisPool(config, rc.getHost(), rc.getPort());
    }
    
    /**
     * 返还到连接池
     * 
     * @param pool 
     * @param redis
     */
    public static void returnResource(Jedis redis) {
        if (redis != null) {
            pool.returnResource(redis);
        }
    }
    /**
     * 释放redis对象
     * @param redis
     */
    public static void returnBrokenResource(Jedis redis){
    	if(redis != null){
    		pool.returnBrokenResource(redis);
    	}
    }
    /**
     * 获取连接
     * @return
     */
    public static Jedis getResource()throws RuntimeException{
    	return pool.getResource();
    }
    
    /**
     * 获取数据
     * @param <T>
     * 
     * @param key
     * @return
     */
    public static <T extends RedisData> T get(T t,String key){
        byte[] bs = get(key);
        
        if(bs == null){
        	return null;
        }else{
        	t.read(bs);
        	return t;
        }
    }
    public static byte[] get(String key){
    	byte[] bs = null;
    	Jedis jedis = null;
        try {
            jedis = getResource();
            bs = jedis.get(key.getBytes());
        } catch (Exception e) {
            //释放redis对象
            returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
        return bs;
    }
    
    /**
     * 添加数据
     * @param key
     * @param value
     * @return
     */
    public static String set(RedisData data){
    	byte[] value = data.write();
        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.set(data.getKey().getBytes(), value);
        } catch (Exception e) {
            //释放redis对象
            returnBrokenResource(jedis);
            e.printStackTrace();
            logger.error(e);
            throw new RuntimeException("redis 连接失败");
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }
    /**
     * 添加数据
     * @param key
     * @param value
     * @return
     */
    public static Long setnx(RedisData data){
    	Long value = 0L;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.setnx(data.getKey().getBytes(), data.write());
        } catch (Exception e) {
            //释放redis对象
            returnBrokenResource(jedis);
            e.printStackTrace();
            logger.error(e);
            throw new RuntimeException("redis 连接失败");
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
        return value;
    }
}
