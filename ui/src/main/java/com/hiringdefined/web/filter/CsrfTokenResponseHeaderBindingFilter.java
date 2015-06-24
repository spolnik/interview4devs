package com.hiringdefined.web.filter;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CsrfTokenResponseHeaderBindingFilter extends OncePerRequestFilter {
    protected static final String REQUEST_ATTRIBUTE_NAME = "_csrf";
    protected static final String RESPONSE_HEADER_NAME = "X-CSRF-HEADER";
    protected static final String RESPONSE_PARAM_NAME = "X-CSRF-PARAM";
    protected static final String RESPONSE_TOKEN_NAME = "X-CSRF-TOKEN";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            javax.servlet.FilterChain filterChain
    ) throws ServletException, IOException {

        CsrfToken csrfToken = (CsrfToken) request.getAttribute(REQUEST_ATTRIBUTE_NAME);
        if (csrfToken != null) {
            response.setHeader(RESPONSE_HEADER_NAME, csrfToken.getHeaderName());
            response.setHeader(RESPONSE_PARAM_NAME, csrfToken.getParameterName());
            response.setHeader(RESPONSE_TOKEN_NAME, csrfToken.getToken());
        }

        // Send the cookie only if the token has changed
        String actualToken = request.getHeader(RESPONSE_TOKEN_NAME);
        if (actualToken == null || !actualToken.equals(csrfToken.getToken())) {
            // Session cookie that will be used by AngularJS
            String pCookieName = "CSRF-TOKEN";
            Cookie cookie = new Cookie(pCookieName, csrfToken.getToken());
            cookie.setMaxAge(-1);
            cookie.setHttpOnly(false);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        filterChain.doFilter(request, response);
    }
}