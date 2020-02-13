package com.anercan.sorucevap.config;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(2)
@WebFilter(urlPatterns = {"/auth"})
public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = ((HttpServletRequest) req).getServletPath();
        chain.doFilter(request, response);
       /* if (path.contains("auth")) {
            //SecurityConfig.checkJWT()
        } else {*/
            //chain.doFilter(request, response);
            //((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");


    }

}

