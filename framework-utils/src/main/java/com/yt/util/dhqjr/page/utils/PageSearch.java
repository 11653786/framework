package com.yt.util.dhqjr.page.utils;

import java.io.Serializable;

/**
 * 分页查询条件封装类
 * 
 * @author liuyijun
 * 
 */
public class PageSearch implements Serializable {

	private static final long serialVersionUID = 587754556498974978L;

	/**
	 * 排序字段
	 */
	private String sort;

	/**
	 * 排序方向
	 */
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

	public int getCurrentResult() {
		return (this.page - 1) * this.rows;
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
	
	
	

}
