package com.allstargh.ssm.pojo;

import java.util.List;

/**
 * 分页对象
 * 
 * <b>从零开始</b>
 * 
 * <ul>
 * <li>总页数</li>
 * <li>每页展示行数</li>
 * <li>是否还有上一页</li>
 * <li>是否还有下一页</li>
 * <li>所获数据</li>
 * <li>当前页码</li>
 * </ul>
 * 
 * @author admin
 * @param <E>
 *
 */
public class Pagination<E> {
	private Integer totalPages;
	private Integer rows;
	private Boolean hasPreviousPage;
	private Boolean hasNextPage;
	private List<E> data;
	private Integer currentPageth;

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Boolean getHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(Boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public Boolean getHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(Boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public Integer getCurrentPageth() {
		return currentPageth;
	}

	public void setCurrentPageth(Integer currentPageth) {
		this.currentPageth = currentPageth;
	}

	@Override
	public String toString() {
		return "Pagination [totalPages=" + totalPages + ", rows=" + rows + ", hasPreviousPage=" + hasPreviousPage
				+ ", hasNextPage=" + hasNextPage + ", data=" + data + ", currentPageth=" + currentPageth + "]";
	}

}
