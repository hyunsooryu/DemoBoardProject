package com.gsshop.config.filter;

import javax.servlet.*;
import java.io.IOException;

public class hyunsooFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Hyunsoo Filter's init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(null == request.getAttribute("data")){
            System.out.println("data is null!!");
            request.setAttribute("data", "filterData");
        }
        chain.doFilter(request, response);
        System.out.println("HYUNSOO FILTER ON 2");
    }

    @Override
    public void destroy() {
        System.out.println("Hyunsoo Filter's destroy");
    }
}
