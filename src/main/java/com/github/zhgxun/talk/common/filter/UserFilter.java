package com.github.zhgxun.talk.common.filter;

import com.github.zhgxun.talk.common.exception.AuthException;
import com.github.zhgxun.talk.common.processor.impl.OauthProcessor;
import com.github.zhgxun.talk.common.util.UserUtil;
import com.github.zhgxun.talk.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户认证拦截器
 */
@WebFilter(urlPatterns = "/*")
@Slf4j
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        UserEntity user = new OauthProcessor().auth(req, res);
        if (user == null) {
//            throw new AuthException("User does not exist");
        }
        try (UserUtil userUtil = new UserUtil(user)) {
            chain.doFilter(req, res);
        } catch (Exception e) {
            throw new AuthException("Failed to save user information", e);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
