package com.example.demo.filter;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class TokenAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    	String authorizationHeader = request.getHeader("Authorization");
    	if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
    	    String token = authorizationHeader.substring(7); // 去掉 "Bearer " 部分
    	    UserLoginDto userLogin = tokenUtil.parseJwt(token);
            if(userLogin==null) {
            	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT 驗證失敗");
            	return;
            }
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userLogin.getId(),null,null);
            SecurityContextHolder.getContext().setAuthentication(authToken);
    	}
        filterChain.doFilter(request,response);
    }
}
