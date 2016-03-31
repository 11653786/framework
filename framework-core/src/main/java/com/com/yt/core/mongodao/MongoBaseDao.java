package com.com.yt.core.mongodao;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * mongoTemplate
 * Created by user on 2015/9/25.
 */
public interface MongoBaseDao<T> {

    public T saveOrUpdate(T bean);

    public T insert(T bean);

    public T getById(int _id);

    public long getCount();

    public void groupBy();
    /**
     * 前面是查询的条件,后面是要修改的数据
     *
     * @param where
     * @param set
     */
    public void update(Criteria criteria,Update update);

    public List<T> getList();

    public void remove(T t);


    public void remove(Query query);

    public T findAndRemove(Query query);

    public void Aggreation(Query query);

    public void distinct();

    public void findField();

    public void in();
}
