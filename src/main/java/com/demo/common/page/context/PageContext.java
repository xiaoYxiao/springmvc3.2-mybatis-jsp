package com.demo.common.page.context;

import com.demo.common.page.model.PageModel;

public class PageContext {
	
	private static ThreadLocal<PageModel> pageModuleThreadLocal = new ThreadLocal<PageModel>();
	
	public static void setPageModel(PageModel pageModel) {
		pageModuleThreadLocal.set(pageModel);
	}
	
	public static PageModel getPageModel() {
		PageModel pageModel = pageModuleThreadLocal.get();
		if(null==pageModel) {
			pageModel = new PageModel();
		}
		return pageModel;
	}
	
	public static void setPageParameters(Integer currentPage, Integer pageSize, String[] propertyAndSort) {
		PageModel pageModel = new PageModel();
		pageModel.setCurrentPage(currentPage-1);
		pageModel.setPageSize(pageSize);
		pageModel.setPropertyOfSort(propertyAndSort[0]);
		pageModel.setSort(propertyAndSort[1]);
		
		PageContext.setPageModel(pageModel);
	}
	
	public static void remove() {
		pageModuleThreadLocal.remove();
	}

}
