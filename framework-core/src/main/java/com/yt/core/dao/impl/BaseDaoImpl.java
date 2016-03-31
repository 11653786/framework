package com.yt.core.dao.impl;

import com.yt.core.dao.BaseDao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by user on 2015/8/11.
 */
@Repository
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {


    private String packageName = "com.yt.entity.";
    private String houzhuiMapper = "Mapper";


    @Resource
    private SqlSession session;


    private Class<T> entityClass;


    private String mapperNamespace;


    public BaseDaoImpl() {

    }

    public String getEntityClass() {
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return entityClass.getSimpleName();
    }

    public String setNameSpace() {
        this.mapperNamespace = packageName + getEntityClass() + houzhuiMapper;
        return mapperNamespace;
    }

    public int insert(T t) {
        return session.insert(setNameSpace() + ".insert", t);
    }

    public int insertSelective(T t) {
        return session.insert(setNameSpace() + ".insertSelective", t);
    }

    public T selectByPrimaryKey(int id) {
        return session.selectOne(setNameSpace() + ".selectByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(T t) {
        return session.insert(setNameSpace() + ".updateByPrimaryKeySelective", t);
    }

    @Override
    public List<T> selectByExample(Object example) {
        return session.selectList(setNameSpace() + ".selectByExample", example);
    }

    public int updateByPrimaryKey(T t) {
        return session.insert(setNameSpace() + ".updateByPrimaryKey", t);
    }

    public int deleteByPrimaryKey(int id) {
        return session.delete(setNameSpace() + ".deleteByPrimaryKey", id);
    }

    public int deleteByExample(Object example) {
        return session.delete(setNameSpace() + ".deleteByExample", example);
    }

    public int countByExample(Object example) {
        return session.selectOne(setNameSpace() + ".countByExample", example);
    }

    @Override
    public int updateByExampleSelective(Object example) {
        return session.update(setNameSpace() + ".updateByExampleSelective", example);
    }

    @Override
    public int updateByExample(Object example) {
        return session.update(setNameSpace() + ".updateByExample", example);
    }
}