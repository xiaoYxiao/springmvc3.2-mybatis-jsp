package com.demo.common.extreme.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.filter.AbstractExportFilter;
import org.extremecomponents.table.filter.ExportResponseWrapper;

import com.demo.common.util.GetSessionUtil;


public class ExtremeExportFilter extends AbstractExportFilter {

	private boolean responseHeadersSetBeforeDoFilter;

    public void init(FilterConfig filterConfig) throws ServletException {
        String responseHeadersSetBeforeDoFilter = filterConfig.getInitParameter("responseHeadersSetBeforeDoFilter");
        if (StringUtils.isNotBlank(responseHeadersSetBeforeDoFilter)) {
            this.responseHeadersSetBeforeDoFilter = new Boolean(responseHeadersSetBeforeDoFilter).booleanValue();
        }
    }
    
    public void destroy() {}

    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain, String exportFileName) throws IOException, ServletException {
    	Integer viewType = GetSessionUtil.getBrowingType((HttpServletRequest)request);
		
    	if (responseHeadersSetBeforeDoFilter) {
    		if(1==viewType) {
    			exportFileName = URLEncoder.encode(exportFileName, "UTF-8");
    		}else{
    			exportFileName = new String(exportFileName.getBytes("UTF-8"), "ISO-8859-1");
    		}
            setResponseHeaders((HttpServletResponse) response, exportFileName);
        }
        chain.doFilter(request, new ExportResponseWrapper((HttpServletResponse) response));
        if (!responseHeadersSetBeforeDoFilter) {
        	if(1==viewType) {
    			exportFileName = URLEncoder.encode(exportFileName, "UTF-8");
    		}else{
    			exportFileName = new String(exportFileName.getBytes("UTF-8"), "ISO-8859-1");
    		}
            setResponseHeaders((HttpServletResponse) response, exportFileName);
        }
    }

}
