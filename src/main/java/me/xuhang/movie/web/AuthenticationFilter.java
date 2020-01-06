package me.xuhang.movie.web;

import me.xuhang.movie.entity.User;
import me.xuhang.movie.security.AdminRequired;
import me.xuhang.movie.security.FreeAccess;
import me.xuhang.movie.security.LoginRequired;
import me.xuhang.movie.utils.ContextUtils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * Created by xuhang on 2019/11/20.
 */
public class AuthenticationFilter extends HandlerInterceptorAdapter {

    @SuppressWarnings("deprecation")
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        FreeAccess freeAccess = method.getMethodAnnotation(FreeAccess.class);
        if (freeAccess != null) {
            return true;
        }

        boolean adminRequired = AnnotationUtils.findAnnotation(method.getBean().getClass(), AdminRequired.class) != null
                || method.getMethodAnnotation(AdminRequired.class) != null;
        boolean loginRequired = AnnotationUtils.findAnnotation(method.getBean().getClass(), LoginRequired.class) != null
                || method.getMethodAnnotation(LoginRequired.class) != null || adminRequired;
        if (loginRequired && !checkLogin(request)) {
            String url = request.getRequestURI();
            String queryString = request.getQueryString();
            if (StringUtils.isNotEmpty(queryString)) {
                url = url + "?" + queryString;
            }

            url = new String(Base64.encodeBase64(url.getBytes()));
            url = URLEncoder.encode(url);

            //If it is an ajax request response header, there will be，x-requested-with
            if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                response.setHeader("sessionStatus", "timeout");//Set the session state in the response header
                return false;
            }

            response.setStatus(401);
            String redirectUrl = "/login?redirect=" + url;

            response.sendRedirect(redirectUrl);
            return false;
        }

        if (adminRequired && !checkAdmin(request)) {
            response.setStatus(403);
            response.sendRedirect("/403");
            return false;
        }


        return true;
    }


    public boolean checkLogin(HttpServletRequest request) {
        return ContextUtils.getSessionUtils(request).getUser() != null;
    }

    public boolean checkAdmin(HttpServletRequest request) {
        User user = ContextUtils.getSessionUtils(request).getUser();
        if (user == null) {
            return false;
        } else {
            return user.isAdmin();
        }
    }

}
