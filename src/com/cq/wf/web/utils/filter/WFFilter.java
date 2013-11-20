package com.cq.wf.web.utils.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class WFFilter extends StrutsPrepareAndExecuteFilter {
    @Override
    public void destroy() {
        super.destroy();

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
            FilterChain arg2) throws IOException, ServletException {

        //TODO user login check will add here
        
        HttpServletRequest request = (HttpServletRequest) arg0;
        String url = request.getRequestURI().toLowerCase();
        if ((url.contains("/admin") || url.contains("/ui/")
                || url.contains("/vaadin/") || url.contains("/service/")) == false) {
            super.doFilter(arg0, arg1, arg2);
        } else {
            arg2.doFilter(arg0, arg1);
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        super.init(arg0);
    }

}
