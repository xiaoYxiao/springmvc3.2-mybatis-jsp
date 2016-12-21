package com.demo.common.page.model;

import java.util.ArrayList;
import java.util.List;

public class PageModel {
	
private Integer totalRecordCount;
	
	private Integer currentPage;
	
	private Integer pageSize;
	
	private Integer totalPageCount;
	
	private String propertyOfSort;
	
	private String sort;
	
	@SuppressWarnings("unchecked")
	private List pageDatas = new ArrayList();

	public Integer getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(Integer totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
	public String getPropertyOfSort() {
		return propertyOfSort;
	}

	public void setPropertyOfSort(String propertyOfSort) {
		this.propertyOfSort = propertyOfSort;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@SuppressWarnings("unchecked")
	public List getPageDatas() {
		return pageDatas;
	}

	@SuppressWarnings("unchecked")
	public void setPageDatas(List pageDatas) {
		this.pageDatas = pageDatas;
	}


}
