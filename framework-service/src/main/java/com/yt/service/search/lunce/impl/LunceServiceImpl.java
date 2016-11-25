package com.yt.service.search.lunce.impl;

import com.yt.service.search.lunce.LunceService;
import com.yt.util.dhqjr.EmptyUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Fieldable;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 全站搜索使用的lunce
 *
 * @author zhangsan
 * @version 1.0
 * @package com.yt.service.search.lunce.impl
 * @date 2016/5/24 0024 9:17
 * @descption: 疯狂的王麻子团队!
 */
//@Service
//@Transactional(propagation = Propagation.REQUIRED)
public class LunceServiceImpl implements LunceService {


    @Override
    public Directory createDir(String lunceIndexPath) {
        //lunce索引document,存放文件夹
        File indexFile = null;
        try {
            indexFile = new File(lunceIndexPath);
            if (!indexFile.exists()) {
                indexFile.mkdir();
            }
            //打开索引文件
            return FSDirectory.open(indexFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建lunce索引,只用创建一次
     *
     * @param rs
     * @param createSearchField  要添加索引的第一个字段,例如id
     * @param createSearchField2 要添加索引的第二个字段,例如:auth_name
     * @throws Exception
     */
    public void createIndex(ResultSet rs, String lunceIndexPath, String createSearchField, String createSearchField2) {
        //创建lunce,document搜索文件存放位置
        Directory directory = createDir(lunceIndexPath);
        //分词器
        Analyzer analyzer = new IKAnalyzer(true);

        //配置IndexWriterConfig
        IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_34, analyzer);
        iwConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        IndexWriter iwriter = null;
        Document doc = null;
        try {
            //写入索引
            iwriter = new IndexWriter(directory, iwConfig);
            //把要搜索的字段和数据写入索引
            while (rs.next()) {
                doc = new Document();
                doc.add(new Field(createSearchField, String.valueOf(rs.getInt(createSearchField)), Field.Store.YES, Field.Index.NOT_ANALYZED));
                doc.add(new Field(createSearchField2, rs.getString(createSearchField2), Field.Store.YES, Field.Index.ANALYZED));
                iwriter.addDocument(doc);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (!EmptyUtil.isEmpty(iwriter)) {
                try {
                    iwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    /**
     * 根据索引进行查询
     *
     * @param searchFieldName 搜索字段名称 例如auth_name或者id
     * @param keyWord         搜索内容 例如员工管理
     */
    public void createSearch(String lunceIndexPath, String searchFieldName, String keyWord) {

        //分词器
        Analyzer analyzer = new IKAnalyzer(true);

        IndexReader ireader = null;
        IndexSearcher isearcher = null;
        //建立内存索引对象,lunce索引文件夹
        Directory directory = null;
        try {
            directory = createDir(lunceIndexPath);
            //搜索过程**********************************
            //实例化搜索器
            ireader = IndexReader.open(directory);
            isearcher = new IndexSearcher(ireader);
            //使用QueryParser查询分析器构造Query对象
            //查询的字段
            QueryParser qp = new QueryParser(Version.LUCENE_34, searchFieldName, analyzer);
            qp.setDefaultOperator(QueryParser.AND_OPERATOR);
            //进行关键字查询
            Query query = qp.parse(keyWord);
            System.out.println("Query条件: = " + query + "(字段:内容)");

            //搜索相似度最高的5条记录,显示条数
            int displayTotal = 5;
            TopDocs topDocs = isearcher.search(query, displayTotal);
            //查询名称数量
            System.out.println("搜索命中总数：" + topDocs.totalHits);
            //输出结果
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            int dataRows = topDocs.totalHits;
            for (int i = 0; i < topDocs.totalHits; i++) {
                if (i < displayTotal) {
                    Document targetDoc = isearcher.doc(scoreDocs[i].doc);
                    //搜索的字段名称
                    for (int a = 0; a < targetDoc.getFields().size(); a++) {
                        Fieldable lunceField = targetDoc.getFields().get(a);
                        //lunce索引中的字段名称和值,例如(id,1)
                        System.out.println(lunceField.name() + "," + lunceField.stringValue());

                    }
                    System.out.println("内容：" + targetDoc.toString());
                }
            }

        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (LockObtainFailedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (ireader != null) {
                try {
                    ireader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (directory != null) {
                try {
                    directory.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
