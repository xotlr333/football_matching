package com.ktds.football.interceptor;

import com.ktds.football.dto.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
        }
        else {
            Member member = (Member) session.getAttribute("memberInfo");
            request.setAttribute("member", member);
        }

        return true;
    }
}
