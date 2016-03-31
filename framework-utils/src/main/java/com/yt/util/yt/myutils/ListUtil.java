package com.yt.util.yt.myutils;

import com.mongodb.DBObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by user on 2015/9/23.
 */
public class ListUtil {

    public static <T> List<T> IteratorToList(Iterable<DBObject> result,Type type) {
        Iterator<DBObject> iter = result.iterator();
        List<DBObject> copy = new ArrayList<DBObject>();
        while (iter.hasNext())
            copy.add(iter.next());
        String jsonStr= JsonUtil.toJson(copy);
        List<T> list=JsonUtil.fromJson(jsonStr,type);
        return list;
    }
}
