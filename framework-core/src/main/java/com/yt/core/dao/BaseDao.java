package com.yt.core.dao;

import java.util.List;

/**
 * http://www.runoob.com/bootstrap/bootstrap-v2-layout.html
 * Created by user on 2015/8/11.
 */
public interface BaseDao<T> {

    public int insert(T t);

    public int insertSelective(T t);

    public T selectByPrimaryKey(int id);

    public int deleteByPrimaryKey(int id);

    public int updateByPrimaryKeySelective(T t);

    public List<T> selectByExample(Object example);

    public int deleteByExample(Object example);

    public int countByExample(Object example);

    public int updateByExampleSelective(Object example);

    public int updateByExample(Object example);
}
