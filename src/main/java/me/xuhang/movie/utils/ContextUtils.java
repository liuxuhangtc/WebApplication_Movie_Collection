package me.xuhang.movie.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import me.xuhang.movie.entity.User;

/**
 * Created by xuhang on 2019/11/20.
 */
public class ContextUtils {


    /**
     * the attribute name of SessionUtils in session
     */
    public static final String SESSIONUTILS = "SessionUtils";


    /**
     * get the login user
     *
     * @param request
     * @return invalid return null
     */
    public static User getUser(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        } else {
            return getSessionUtils(session).getUser();
        }
    }

    /**
     * get user Id
     *
     * @param request
     * @return invalid return null
     */
    public static String getUserId(final HttpServletRequest request) {
        User user = getUser(request);
        return user == null ? null : user.getId();
    }

    /**
     * get SessionUtils
     *
     * @param request
     * @return
     */
    public static SessionUtils getSessionUtils(final HttpServletRequest request) {
        return getSessionUtils(request.getSession());
    }

    public static SessionUtils getSessionUtils(final HttpSession session) {
        if (session.getAttribute(SESSIONUTILS) == null) {
            SessionUtils sessionUtils = new SessionUtils();
            session.setAttribute(SESSIONUTILS, sessionUtils);
            return sessionUtils;
        } else {
            return (SessionUtils) session.getAttribute(SESSIONUTILS);
        }
    }

    public static void storeInSession(final HttpServletRequest request,
                                      final String name,
                                      final Object value) {
        request.getSession().setAttribute(name, value);
    }

    /**
     * get Session object info
     *
     * @param <T>
     * @param request
     * @param name
     * @param clazz
     * @param keep
     * @return null or object
     */
    @SuppressWarnings("unchecked")
	public static <T> T getFromSession(final HttpServletRequest request,
                                       final String name,
                                       final Class<T> clazz,
                                       final boolean keep) {
        T result = null;
        HttpSession session = request.getSession();
        if (session.getAttribute(name) != null) {
            result = (T) session.getAttribute(name);
            if (!keep) {
                session.removeAttribute(name);
            }
        }
        return result;
    }
}
