package com.sunjoy.framework.dao.paging;

import java.io.Serializable;

public class PageInfo implements Serializable {
	/**
	 * @serialField serialVersionUID
	 */
	private static final long serialVersionUID = -8362464289167998023L;

	/** 当前页 */
	protected int currentPage = 1;

	/** 每页记录数，默认10条 */
	protected int pageSize = 10;
	
	/**
	 * 获取当前页
	 *
	 * @return 当前页
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 设置当前页
	 *
	 * @param currentPage
	 *            当前页
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 获取每页记录数
	 *
	 * @return 每页记录数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页记录数
	 *
	 * @param pageSize
	 *            每页记录数
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
