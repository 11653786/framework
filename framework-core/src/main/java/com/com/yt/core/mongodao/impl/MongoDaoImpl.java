package com.com.yt.core.mongodao.impl;

import com.mongodb.*;
import com.com.yt.core.mongodao.MongoBaseDao;
import com.yt.entity.mongo.Student;
import com.yt.util.mongoUtil.MongoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * mongoTemplate
 * Created by user on 2015/9/25.
 */
@Repository
@Transactional
public class MongoDaoImpl<T> implements MongoBaseDao<T> {

    private Class<T> entityClass;
    private int _id;
    @Autowired
    protected MongoTemplate mongoTemplate;


    public Class<T> getEntityClass() {
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        // System.out.println("className: " + this.entityClass.getSimpleName());
        return entityClass;
    }


    public T saveOrUpdate(T bean) {
        mongoTemplate.save(bean);
        return bean;
    }

    public T insert(T bean) {
        mongoTemplate.insert(bean);
        return bean;
    }

    public T getById(int _id) {
        Query query=new Query();
        Criteria where=new Criteria().where("_id").is(_id);
        query.addCriteria(where);
      return mongoTemplate.findOne(query, getEntityClass());
    }

    public long getCount() {
     return mongoTemplate.count(new Query(), getEntityClass());
    }


    //http://my.oschina.net/zhzhenqin/blog/99846
    public void groupBy() {
        String reduce = "function(doc, aggr){aggr.count += 1;}";
        Query query = Query.query(Criteria.where("age").exists(true));
        //显示age字段,,查询的条件是query,重新组建count字段,初始值为0，递增规则
        DBObject result = getDbCollection().group(new BasicDBObject("age", 1), query.getQueryObject(),new BasicDBObject("count", 0), reduce);
        Map map = result.toMap();
        System.out.println(map);
    }

    public void distinct(){
        List result=getDbCollection().distinct("name");
        for(Object obj:result){
            System.out.println(obj);
        }
    }

    public void update(Criteria criteria,Update update) {
        mongoTemplate.updateMulti(new Query(criteria) ,update,getEntityClass());
    }

    public List<T> getList() {
        Query query=new Query();
       List<T> list= mongoTemplate.find(query,getEntityClass());
        return list;
    }

    public void remove(T t) {
        mongoTemplate.remove(t,getCollectionName());
    }

    public void remove(Query query){
        mongoTemplate.remove(query,getEntityClass());
    }

    public T findAndRemove(Query query) {
        return mongoTemplate.findAndRemove(query,getEntityClass());
    }

    public void Aggreation(Query query) {
        try{
            Aggregation agg=Aggregation.newAggregation(Aggregation.match(Criteria.where("_id").gt(5)));
            AggregationResults<T> results = mongoTemplate.aggregate(agg, getCollectionName(), getEntityClass());
            List<T> list=results.getMappedResults();
            for(int a=0;a<list.size();a++){
                System.out.println(list.get(a));
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }


    public void findField() {
        Query query=new Query();
        query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "_id")));
        query.skip(0);
        query.limit(10);
        Criteria criteria=Criteria.where("name").is("张三");
        query.addCriteria(criteria);
        List<Student> list= mongoTemplate.find(query, Student.class);


        BasicDBObject fields= new BasicDBObject();
        fields.put("_id", 1);
        fields.put("name", 1);
        fields.put("sex", 1);
        BasicDBObject where= new BasicDBObject();
        where.append("_id", new BasicDBObject(MongoUtils.$gte, 4));
        where.append("name", new BasicDBObject(MongoUtils.$eq, "张三"));
        //每页显示2条
        DBCursor cursor =getDbCollection().find(where,fields);
        cursor.skip(0);
        cursor.limit(10);
        cursor.sort(new BasicDBObject("_id",-1));
        List<DBObject> list2=cursor.toArray();
        for(DBObject dbObject:list2){
            System.out.println(dbObject);
        }
    }


    /**
     * 哥哥的代码写的很巧妙啊...
     * 获取mongo实体的名称
     * @return
     */
    public String getCollectionName() {
        Document document = getEntityClass().getAnnotation(Document.class);
        String collectionName = null;
        if (!StringUtils.isEmpty(document)) {
            collectionName = document.collection();
            if (StringUtils.isEmpty(collectionName)) collectionName = getEntityClass().getName();
        } else {
            collectionName = getEntityClass().getSimpleName();
        }
        return collectionName;
    }


    public void in(){

//        DBObject queryCondition = new BasicDBObject();
//        //age in [13, 47]
//        queryCondition = new BasicDBObject();
//        BasicDBList values = new BasicDBList();
//        values.add(13);
//        values.add(47);
//        queryCondition.put("age", new BasicDBObject("$in", values));
//
//        DBCursor dbCursor = coll.find(queryCondition);
        BasicDBObject  in = new BasicDBObject();
        in.put("name",new BasicDBObject(MongoUtils.$in, new String[] {"张三"}));
        DBCursor cursor= getDbCollection().find(in);
        List<DBObject> list=cursor.toArray();
        System.out.println(list.size());
        for(DBObject db:list){
        System.out.println(db);
        }

    }


    public DBCollection getDbCollection() {
        return mongoTemplate.getDb().getCollection(getCollectionName());
    }

}
