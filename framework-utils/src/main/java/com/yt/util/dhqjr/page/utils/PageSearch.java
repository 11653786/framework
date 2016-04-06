package com.yt.util.dhqjr.page.utils;

import com.yt.util.dhqjr.EmptyUtil;

import java.io.Serializable;

/**
 * 分页查询条件封装类
 *
 * @author liuyijun
 */
public class PageSearch implements Serializable {

    private static final long serialVersionUID = 587754556498974978L;

    //每页显示数量
    private final static Integer pageSize = 10;

    /**
     * 排序字段
     */
    private String sort;

    /**
     * 排序方式:asc,desc
     */
    private String order;

    /**
     * 排序方向
     */
    @Deprecated
    private String direction = "desc";

    /**
     * 分页请求时当前页变量
     */
    private Integer page = 1;
    /**
     * 分页请求时每页显示数量变量
     */
    private Integer rows = 10;
    /**
     * 分页请求时总条数
     */
    private int totalResult;

    private int currentResult;


    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;

    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;

    }

    //返回当前分页数量
    public int getCurrentResult() {
        this.currentResult = (this.page - 1) * this.rows;
        return this.currentResult;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }


    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    //当前分页
    private final static Integer pages = 1;

    public static PageSearch setPageInfo(PageSearch search) {
        if (EmptyUtil.isEmpty(search)) {
            search = new PageSearch();
            search.setPage(pages);
            search.setRows(pageSize);
        } else if (EmptyUtil.isNotEmpty(search)
                && EmptyUtil.isNotEmpty(search.getPage())
                && EmptyUtil.isNotEmpty(search.getRows())) {
            if (search.getPage().intValue() < 1) {
                search.setPage(pages);
            }
            if (search.getRows().intValue() < 1) {
                search.setRows(pageSize);
            }
        } else if (EmptyUtil.isNotEmpty(search)
                && EmptyUtil.isEmpty(search.getPage())
                && EmptyUtil.isEmpty(search.getRows())) {
            search.setPage(pages);
            search.setRows(pageSize);
        }
        return search;
    }


}
