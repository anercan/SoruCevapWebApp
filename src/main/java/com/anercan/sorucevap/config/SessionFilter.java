package com.anercan.sorucevap.config;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@Order(2)
@WebFilter(urlPatterns = {"/auth"})
public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getServletPath();
        if (/*path.contains("auth")*/false) {
            Cookie token = Arrays.stream(((HttpServletRequest) request).getCookies()).
                    filter(cookie -> cookie.getName().equals("token")).findFirst().orElse(null);
            if (token != null && SecurityConfig.checkJWT(token.getValue())) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
            }
        } else {
            chain.doFilter(request, response);
        }

    }

}

