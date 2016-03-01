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
     * ����redis���ӳ�
     * 
     * @param ip
     * @param port
     * @return JedisPool
     */
    public static void init(RedisConfig rc){
            JedisPoolConfig config = new JedisPoolConfig();
            //����һ��pool�ɷ�����ٸ�jedisʵ����ͨ��pool.getResource()����ȡ��
            //�����ֵΪ-1�����ʾ�����ƣ����pool�Ѿ�������maxActive��jedisʵ�������ʱpool��״̬Ϊexhausted(�ľ�)��
//            config.setMaxActive(500);
            //����һ��pool����ж��ٸ�״̬Ϊidle(���е�)��jedisʵ����
            config.setMaxIdle(rc.getMaxIdle());
            //��ʾ��borrow(����)һ��jedisʵ��ʱ�����ĵȴ�ʱ�䣬��������ȴ�ʱ�䣬��ֱ���׳�JedisConnectionException��
//            config.setMaxWait(1000 * 100);
            //��borrowһ��jedisʵ��ʱ���Ƿ���ǰ����validate���������Ϊtrue����õ���jedisʵ�����ǿ��õģ�
            config.setTestOnBorrow(true);
            pool = new JedisPool(config, rc.getHost(), rc.getPort());
    }
    
    /**
     * ���������ӳ�
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
     * �ͷ�redis����
     * @param redis
     */
    public static void returnBrokenResource(Jedis redis){
    	if(redis != null){
    		pool.returnBrokenResource(redis);
    	}
    }
    /**
     * ��ȡ����
     * @return
     */
    public static Jedis getResource()throws RuntimeException{
    	return pool.getResource();
    }
    
    /**
     * ��ȡ����
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
            //�ͷ�redis����
            returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //���������ӳ�
            returnResource(jedis);
        }
        return bs;
    }
    
    /**
     * �������
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
            //�ͷ�redis����
            returnBrokenResource(jedis);
            e.printStackTrace();
            logger.error(e);
            throw new RuntimeException("redis ����ʧ��");
        } finally {
            //���������ӳ�
            returnResource(jedis);
        }
    }
    /**
     * �������
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
            //�ͷ�redis����
            returnBrokenResource(jedis);
            e.printStackTrace();
            logger.error(e);
            throw new RuntimeException("redis ����ʧ��");
        } finally {
            //���������ӳ�
            returnResource(jedis);
        }
        return value;
    }
}
