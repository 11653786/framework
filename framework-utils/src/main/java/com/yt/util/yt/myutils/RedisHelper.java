package com.yt.util.yt.myutils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by leo on 15/7/24.
 */
public class RedisHelper {

    public static JedisPool jedisPool = null;


    public final static String  host = "127.0.0.1";
    public final static int port = 6379;
    public final static int maxTotal =500;
    public final static int maxIdle = 300;
    public final static int minIdle = 100;
    public final static int maxWait = 1000;
    public final static boolean testOnBorrow =true;

    /**
     * 初始化Redis连接池
     */
    private static void initialPool() {

        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(maxTotal);
            config.setMaxIdle(maxIdle);
            config.setMinIdle(minIdle);
            config.setMaxWaitMillis(maxWait);
            config.setTestOnBorrow(testOnBorrow);
            jedisPool = new JedisPool(config, host, port, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (jedisPool == null) {
            initialPool();
        }
    }

    public static synchronized Jedis getJedis() {
        if (jedisPool == null) {
            poolInit();
        }
        try {
            Jedis jedis = jedisPool.getResource();
            return jedis;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //设置一个key的value值
    public static String set(int dataSource, String key, String value) {
        Jedis jedis = null;
        String response = null;
        try {
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            response = jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return response;
    }

    /**
     * set Object
     */
    public static String set(int dateSource, String key, Object value) {
        Jedis jedis = null;
        String response = null;
        try {
            String objectJson = JsonUtil.toJson(value);
            jedis = getJedis();
            if (dateSource != 0) {
                jedis.select(dateSource);
            }
            response = jedis.set(key, objectJson);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return response;
    }

    //获取key的值
    public static String get(int dataSource, String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return value;
    }

    /**
     * get Object
     */
    public static <T> T get(int dataSource, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            String value = jedis.get(key);
            return JsonUtil.fromJson(value, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    //删除一个key
    public static boolean del(int dataSource, String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            jedis.del(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
    }

    //设置hash里面一个字段的值
    public static Long hset(int dataSource, String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            Long count = jedis.hset(key, field, value);
            return count;
        } catch (JedisException e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * hset Object
     */
    public static boolean hset(int dataSource, String key, String field, Object value) {
        Jedis jedis = null;
        try {
            String objectJson = JsonUtil.toJson(value);
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            jedis.hset(key, field, objectJson);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
    }

    //读取哈希域的的值
    public static String hget(int dataSource, String key, String field) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            value = jedis.hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return value;
    }

    /**
     * hget Object
     */
    public static <T> T hget(int dataSource, String key, String field, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            String value = jedis.hget(key, field);
            return JsonUtil.fromJson(value, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    //删除一个哈希域
    public static boolean hdel(int dataSource, String key, String field) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            jedis.hdel(key, field);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
    }

    //从队列的右边入队一个元素
    public static boolean rpush(int dataSource, String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            jedis.rpush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
    }

    public static boolean rpush(int dataSource, String key, Object value) {
        Jedis jedis = null;
        try {
            String objectJson = JsonUtil.toJson(value);
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            jedis.rpush(key, objectJson);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
    }

    //从列表中获取指定返回的元素
    public static List<String> lrange(int dataSource, String key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            List<String> lists = jedis.lrange(key, start, end);
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    //修剪到指定范围内的清单
    public static boolean ltrim(int dataSource, String key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            jedis.ltrim(key, start, end);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
    }

    //整数原子减1
    public static Long decr(int dataSource, String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            return jedis.decr(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            jedis.close();
        }
    }

    //执行原子加1操作
    public static Long incr(int dataSource, String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            return jedis.incr(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            jedis.close();
        }
    }

    //设置一个key的过期的秒数
    public static boolean expire(int dataSource, String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            jedis.expire(key, seconds);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
    }

    public static boolean hexists(int dataSource, String key, String field) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            boolean flag = jedis.hexists(key, field);
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
    }

    /**
     * 批量删除缓存中得对象，根据key
     *
     * @param key
     * @return
     */
    public static boolean delKeys(int dataSource, String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (dataSource != 0) {
                jedis.select(dataSource);
            }
            Set<String> keys = jedis.keys(key);
            Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                String Keys = it.next();
                jedis.del(Keys);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
    }
}



