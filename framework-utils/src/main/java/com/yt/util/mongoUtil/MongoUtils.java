package com.yt.util.mongoUtil;

/**
 *
 *
 * http://blog.csdn.net/miyatang/article/details/20997313,sql和mongo对照表
 http://docs.mongodb.org/manual/reference/operator/query/ 官方文档
 http://www.2cto.com/database/201412/365520.html 官方实例中文解释
 * WHERE           $match
     GROUP BY        $group
     HAVING          $match,这个要等groupby用了以后再用
     SELECT          $project,显示单个字段,不写就全显示
     ORDER BY        $sort
     LIMIT           $limit
     SUM()           $sum
     COUNT()         $sum
 *
 * Created by user on 2015/9/21.
 */
public class MongoUtils {

    public static String $match="$match";       //相当于sql中的where

    public static String $project="$project";   //不写就显示全部字段，写了为1就显示当前字段 *和字段名的关系

    public static String $eq="$eq";             //等于

    public static String $gt="$gt";             //大于

    public static String $gte="$gte";             //大于等于

    public static String $group="$group";       //分组查询用的,group by

    public static String $orderBy="$sort";

    public static String $limit="$limit";       //pageSize

    public static String $skip="$skip";          //page

    public static String $set="$set";           //set

    public static String $and="$and";           //用法和sql一样先写字段在写

    public static String $or="$or";             //or查询

    public static String $lt="$lt";             //小于

    public static String $ne="$ne";             //不等于

    public static String $in="$in";             //在

    public static String $avg="$avg";           //平均

    public static String $sum="$sum";


}
