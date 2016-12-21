package com.demo.common.page.context;

import javax.servlet.http.HttpServletRequest;

import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.Sort;
import org.extremecomponents.table.limit.TableLimit;
import org.extremecomponents.table.limit.TableLimitFactory;

public class ExtremeContext {
	
	public static Limit getLimit(HttpServletRequest request) {
		String tableId = getTableId(request);
		Context context = new HttpServletRequestContext(request);
		LimitFactory limitFactory = new TableLimitFactory(context, tableId);
		TableLimit limit = new TableLimit(limitFactory);
		return limit;
	}
	
	public static Integer getPageSize(HttpServletRequest request) {
		String tableId = getTableId(request);
		Integer defaultCurrentRowsDisplay = 10;
		String currentRowsDisplay = request.getParameter(tableId + "_" + TableConstants.CURRENT_ROWS_DISPLAYED);
		if(null != currentRowsDisplay && !"".equals(currentRowsDisplay)) {
			defaultCurrentRowsDisplay = Integer.valueOf(currentRowsDisplay);
		}
		return defaultCurrentRowsDisplay;
	}
	
	public static Integer getCurrentPage(HttpServletRequest request) {
		Limit limit = getLimit(request);
		return limit.getPage();
	}
	
	public static String[] getPropertyAndSort(HttpServletRequest request) {
		Limit limit = getLimit(request);
		Sort sort = limit.getSort();
		String property = sort.getProperty();
		String sortOrder = sort.getSortOrder();
		return new String[]{property, sortOrder};
	}
	
	private static String getTableId(HttpServletRequest request) {
		String tableId = request.getParameter(TableConstants.EXTREME_COMPONENTS_INSTANCE);
		if(null==tableId || "".equals(tableId)) {
			tableId = TableConstants.EXTREME_COMPONENTS;
		}
		return tableId;
	}

}
