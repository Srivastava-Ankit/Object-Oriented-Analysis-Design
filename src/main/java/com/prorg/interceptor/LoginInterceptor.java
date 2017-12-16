package com.prorg.interceptor;

import com.prorg.helper.Constants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute(Constants.SessionKeys.LOGGED_IN_USER) == null) {
            String referer = request.getRequestURL().toString();
            request.setAttribute(Constants.RequestAttributes.REFERER, referer);
            response.sendRedirect(Constants.Route.LOGIN);
            return false;
        }
        return true;
    }
}
