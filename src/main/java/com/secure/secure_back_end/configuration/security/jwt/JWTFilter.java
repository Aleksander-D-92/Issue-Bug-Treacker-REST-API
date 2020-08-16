package com.secure.secure_back_end.configuration.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends GenericFilterBean
{
    private final TokenProvider tokenProvider;

    public JWTFilter(TokenProvider tokenProvider)
    {
        this.tokenProvider = tokenProvider;
    }

    //It's called one every request
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException
    {
        try
        {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String jwt = resolveToken(httpServletRequest); //get the jwt value from the "Bearer " header

            if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) //parse token see if it was tempered with
            {
                Authentication authentication = this.tokenProvider.getAuthentication(jwt); //create Authentication object and add it in security context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            //IF there is not token, just filter
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (ExpiredJwtException eje)
        {
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    //get the jwt value from the "Bearer " header
    private String resolveToken(HttpServletRequest request)
    {
        String bearerToken = request.getHeader(JWTConfigurer.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
        {
            return bearerToken.substring(7);
        }
        return null;
    }
}


