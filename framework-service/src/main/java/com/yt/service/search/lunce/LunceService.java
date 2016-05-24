package com.yt.service.search.lunce;


import org.apache.lucene.store.Directory;

import java.sql.ResultSet;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.service.search.lunce
 * @date 2016/5/24 0024 9:16
 * @descption: 疯狂的王麻子团队!
 */
public interface LunceService {

    /**
     * lunce,创建索引文件位置
     *
     * @param lunceIndexPath
     * @return
     */
    public Directory createDir(String lunceIndexPath);


    /**
     * 创建lunce索引,只用创建一次
     *
     * @param rs
     * @param lunceIndexPath     索引文件地址
     * @param createSearchField  要添加索引的第一个字段,例如id
     * @param createSearchField2 要添加索引的第二个字段,例如:auth_name
     * @throws Exception
     */
    public void createIndex(ResultSet rs, String lunceIndexPath, String createSearchField, String createSearchField2);


    /**
     * 根据索引进行查询
     *
     * @param lunceIndexPath  索引文件地址
     * @param searchFieldName 搜索字段名称 例如auth_name或者id
     * @param keyWord         搜索内容 例如员工管理
     */
    public void createSearch(String lunceIndexPath, String searchFieldName, String keyWord);
}
