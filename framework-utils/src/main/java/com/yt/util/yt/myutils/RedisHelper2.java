package com.yt.util.yt.myutils;

import redis.clients.jedis.*;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Created by snifferhu on 15/10/2.
 */
public class RedisHelper2 {
    private static JedisPool jedisPool = null;
    private static JedisPoolConfig config = null;
    private static Properties properties = null;

    /**
     * 静态代码块初始化连接池
     */
    static {
        config = new JedisPoolConfig();//初始化连接池
        config.setMaxTotal(RedisHelper.maxTotal);
        config.setMaxIdle(RedisHelper.maxIdle);
        config.setMinIdle(RedisHelper.minIdle);
        config.setMaxWaitMillis(RedisHelper.maxWait);
        config.setTestOnBorrow(RedisHelper.testOnBorrow);
        jedisPool = new JedisPool(config, RedisHelper.host, RedisHelper.port, 0);
    }

    /**
     * 内部方法接口，在闭包中实现具体调用
     *
     * @param <E> 传输主调引用
     * @param <T> 返回值范型
     */
    private interface Function<E, T> {
        T callback(E e);
    }
    /**
     * 装饰器封装获取连接池对象，释放连接
     *
     * @param fun
     * @param <T>
     * @return
     */
    private static <T> T execute(Function<Jedis, T> fun) {
        if (jedisPool == null) {
            poolInit();
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return fun.callback(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return null;
    }

    private static void poolInit() {
        config = new JedisPoolConfig();//初始化连接池
        config.setMaxTotal(RedisHelper.maxTotal);
        config.setMaxIdle(RedisHelper.maxIdle);
        config.setMinIdle(RedisHelper.minIdle);
        config.setMaxWaitMillis(RedisHelper.maxWait);
        config.setTestOnBorrow(RedisHelper.testOnBorrow);
        jedisPool = new JedisPool(config,RedisHelper.host,RedisHelper.port,0);
    }

    /**
     * 其实我的内心深处是挣扎的，要不要写这个方法
     * 如果已有的封装不适用，建议进来util加个方法
     *
     * @return
     */
    public static Jedis getJedis() {
        /*if (null == jedisPool) {
            poolInit();
        }
        try {
            return jedisPool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/
        return jedisPool.getResource();
    }

    /**
     * 封装管道对象，隐藏开启关闭管道方法
     */
    public static class PipelineWrapper {
        private Jedis jedis;
        private Pipeline pipeline;

        public PipelineWrapper() {
            jedis = jedisPool.getResource();
            pipeline = jedis.pipelined();
        }

        public PipelineWrapper(int dataSource) {
            jedis = jedisPool.getResource();
            jedis.select(dataSource);
            pipeline = jedis.pipelined();
        }

        public Pipeline getPipeline() {
            return pipeline;
        }

        public void close() {
            pipeline.sync();
            jedis.close();
        }

        public List<Object> closeAndReturnAll() {
            try {
                return pipeline.syncAndReturnAll();
            } finally {
                jedis.close();
            }
        }
    }

    /**
     * 默认获取管道对象静态工厂
     *
     * @return
     */
    public static PipelineWrapper getPipelineWrapper() {
        return new PipelineWrapper();
    }

    /**
     * 选择redis库，获取管道对象静态工厂
     *
     * @param dataSource
     * @return
     */
    public static PipelineWrapper getPipelineWrapper(int dataSource) {
        return new PipelineWrapper(dataSource);
    }

    /**
     * 封装事务对象，隐藏开启关闭管道方法
     */
    public static class MultiWrapper {
        private Jedis jedis;
        private Transaction multi;

        public MultiWrapper() {
            jedis = jedisPool.getResource();
            multi = jedis.multi();
        }

        public MultiWrapper(int dataSource) {
            jedis = jedisPool.getResource();
            jedis.select(dataSource);
            multi = jedis.multi();
        }

        public Jedis getJedis() {
            return jedis;
        }

        public Transaction getMulti(){return multi;}

        public List<Object> exec() {
            try {
                return multi.exec();
            } finally {
                jedis.close();
            }
        }

        public List<Response<?>> execGetResponse() {
            try {
                return multi.execGetResponse();
            } finally {
                jedis.close();
            }
        }
    }

    /**
     * 默认获取事务对象静态工厂
     *
     * @return
     */
    public static MultiWrapper getMultiWrapper() {
        return new MultiWrapper();
    }

    /**
     * 选择redis库，获取事务对象静态工厂
     *
     * @param dataSource
     * @return
     */
    public static MultiWrapper getMultiWrapper(int dataSource) {
        return new MultiWrapper(dataSource);
    }

    public static String set(final String key, final String value) {
        return execute(new Function<Jedis, String>() {
            public String callback(Jedis jedis) {
                return jedis.set(key, value);
            }
        });
    }

    /**
     * @param key   键
     * @param value 传入对象，转化为json
     * @return
     */
    public static String set(final String key, final Object value) {
        return set(key, JsonUtil.toJson(value));
    }

    public static String set(final int dataSource, final String key, final String value) {
        return execute(new Function<Jedis, String>() {
            public String callback(Jedis jedis) {
                jedis.select(dataSource);
                return jedis.set(key, value);
            }
        });
    }

    public static String set(final int dataSource, final String key, final Object value) {
        return set(dataSource, key, JsonUtil.toJson(value));
    }

    public static String get(final String key) {
        return execute(new Function<Jedis, String>() {
            public String callback(Jedis jedis) {
                return jedis.get(key);
            }
        });
    }

    public static String get(final int dataSource, final String key) {
        return execute(new Function<Jedis, String>() {
            public String callback(Jedis jedis) {
                jedis.select(dataSource);
                return jedis.get(key);
            }
        });
    }

    public static <T> T get(final String key, final Class<T> clazz) {
        return execute(new Function<Jedis, T>() {
            public T callback(Jedis jedis) {
                String value = jedis.get(key);
                return JsonUtil.fromJson(value, clazz);
            }
        });
    }

    public static <T> T get(final int dataSource, final String key, final Class<T> clazz) {
        return execute(new Function<Jedis, T>() {
            public T callback(Jedis jedis) {
                jedis.select(dataSource);
                String value = jedis.get(key);
                return JsonUtil.fromJson(value, clazz);
            }
        });
    }

    public static Boolean del(final int dataSource, final String key) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.select(dataSource);
                    jedis.del(key);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public static Boolean del(final String key) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.del(key);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public static Long hset(final String key, final String field, final String value) {
        return execute(new Function<Jedis, Long>() {
            public Long callback(Jedis jedis) {
                return jedis.hset(key, field, value);
            }
        });
    }

    public static Long hset(final int dataSource, final String key, final String field, final String value) {
        return execute(new Function<Jedis, Long>() {
            public Long callback(Jedis jedis) {
                jedis.select(dataSource);
                return jedis.hset(key, field, value);
            }
        });
    }

    public static Long hset(final String key, final String field, final Object value) {
        return hset(key, field, JsonUtil.toJson(value));
    }

    public static Long hset(final int dataSource, final String key, final String field, final Object value) {
        return hset(dataSource, key, field, JsonUtil.toJson(value));
    }

    public static String hget(final String key, final String field) {
        return execute(new Function<Jedis, String>() {
            public String callback(Jedis jedis) {
                return jedis.hget(key, field);
            }
        });
    }

    public static String hget(final int dataSource, final String key, final String field) {
        return execute(new Function<Jedis, String>() {
            public String callback(Jedis jedis) {
                jedis.select(dataSource);
                return jedis.hget(key, field);
            }
        });
    }

    public static <T> T hget(final String key, final String field, final Class<T> clazz) {
        return execute(new Function<Jedis, T>() {
            public T callback(Jedis jedis) {
                return JsonUtil.fromJson(jedis.hget(key, field), clazz);
            }
        });
    }

    public static <T> T hget(final int dataSource, final String key, final String field, final Class<T> clazz) {
        return execute(new Function<Jedis, T>() {
            public T callback(Jedis jedis) {
                jedis.select(dataSource);
                return JsonUtil.fromJson(jedis.hget(key, field), clazz);
            }
        });
    }

    public static Boolean hdel(final int dataSource, final String key, final String field) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.select(dataSource);
                    jedis.hget(key, field);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public static Boolean hdel(final String key, final String field) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.hget(key, field);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public static Boolean rpush(final String key, final String value) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.hget(key, value);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public static Boolean rpush(final int dataSource, final String key, final String value) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.select(dataSource);
                    jedis.rpush(key, value);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }


    public static Boolean rpush(final String key, final Object value) {
        return rpush(key, JsonUtil.toJson(value));
    }

    public static Boolean rpush(final int dataSource, final String key, final Object value) {
        return rpush(dataSource, key, JsonUtil.toJson(value));
    }

    public static String lpop(final int dataSource,final String key){
        return execute(new Function<Jedis, String>() {
            public String callback(Jedis jedis) {
                try {
                    jedis.select(dataSource);
                    return jedis.lpop(key);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "";
                }
            }
        });
    }

    public static List<String> lrange(final String key, final int start, final int end) {
        return execute(new Function<Jedis, List<String>>() {
            public List<String> callback(Jedis jedis) {
                return jedis.lrange(key, start, end);

            }
        });
    }

    public static List<String> lrange(final int dataSource, final String key, final int start, final int end) {
        return execute(new Function<Jedis, List<String>>() {
            public List<String> callback(Jedis jedis) {
                jedis.select(dataSource);
                return jedis.lrange(key, start, end);

            }
        });
    }

    public static Boolean ltrim(final String key, final int start, final int end) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.ltrim(key, start, end);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public static Boolean ltrim(final int dataSource, final String key, final int start, final int end) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.select(dataSource);
                    jedis.ltrim(key, start, end);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public static Boolean decr(final String key) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.decr(key);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public static Boolean decr(final int dataSource, final String key) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.select(dataSource);
                    jedis.decr(key);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public static Boolean incr(final String key) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.incr(key);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public static Boolean incr(final int dataSource, final String key) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.select(dataSource);
                    jedis.incr(key);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public static Boolean expire(final String key, final int seconds) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.expire(key, seconds);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public static Boolean expire(final int dataSource, final String key, final int seconds) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.select(dataSource);
                    jedis.expire(key, seconds);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public static Boolean hexists(final String key, final String field) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.hexists(key, field);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public static Boolean hexists(final int dataSource, final String key, final String field) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.select(dataSource);
                    jedis.hexists(key, field);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public static Boolean delKeys(final int dataSource, final String key) {
        return execute(new Function<Jedis, Boolean>() {
            public Boolean callback(Jedis jedis) {
                try {
                    jedis.select(dataSource);
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
                }
            }
        });
    }

    /**
     * 通道调用示例
     */
    private static void pipe() {
        long begin = System.currentTimeMillis();
        PipelineWrapper pw = null;
        try {
            pw = RedisHelper2.getPipelineWrapper();
            for (int i = 0; i < 5000; i++) {
                pw.getPipeline().set("a1", String.valueOf(i));
                pw.getPipeline().set("a2", String.valueOf(i));
                pw.getPipeline().set("a3", String.valueOf(i));
            }
        } finally {
            pw.close();
        }
    }

    /**
     * 事务调用示例
     *
     * @return
     */
    private static List<Object> mulit() {
        MultiWrapper mw = null;
        try {
            mw = RedisHelper2.getMultiWrapper();
            for (int i = 0; i < 5; i++) {
                mw.getJedis().set("a1", String.valueOf(i));
                mw.getJedis().set("a2", String.valueOf(i));
                mw.getJedis().set("a3", String.valueOf(i));
            }
        } finally {
            return mw.exec();
        }
    }

    private static void noPipe() {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            RedisHelper2.set("a1", String.valueOf(i));
            RedisHelper2.set("a2", String.valueOf(i));
            RedisHelper2.set("a3", String.valueOf(i));
        }
    }

    public static void main(String[] argvs) {
        RedisHelper2.pipe();
        RedisHelper2.noPipe();

    }
}
