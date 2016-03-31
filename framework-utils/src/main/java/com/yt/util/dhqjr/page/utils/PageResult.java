package com.yt.util.dhqjr.page.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询结果集封装对象
 * 
 * @author liuyijun
 * 
 * @param <T>
 */
public class PageResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -735585916841299611L;

	/**
	 * 总记录数
	 */
	private long total;

	/**
	 * 每页的数据
	 */
	private List<T> rows;
	
	

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	
	
	

}
