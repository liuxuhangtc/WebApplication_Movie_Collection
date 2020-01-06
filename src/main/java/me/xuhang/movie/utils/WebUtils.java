package me.xuhang.movie.utils;

import nl.captcha.Captcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * Created by xuhang on 2019/11/20.
 */
public class WebUtils {
    static Logger logger = LoggerFactory.getLogger(WebUtils.class);

    private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    private static final Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);

    /**
     * Check if the input captcha is consistent with the session
     *
     * @param request
     * @param captcha
     * @return true or false
     */
    public static boolean checkCaptcha(HttpServletRequest request, String captcha) {
        Captcha c = (Captcha) request.getSession().getAttribute(Captcha.NAME);
        if (c != null && c.getAnswer().toUpperCase().equals(captcha.toUpperCase())) {
            request.getSession().removeAttribute(Captcha.NAME);
            return true;
        }
        return false;
    }

    /**
     * Get ip address
     *
     * @param request
     * @return
     */
    public static String getIP(HttpServletRequest request) {
        String IP = request.getRemoteAddr();
        String forwarded = request.getHeader("x-forwarded-for");

        if (forwarded != null) {
            forwarded = forwarded.split(",", 2)[0];
            if (pattern.matcher(forwarded).matches()) {
                return forwarded;
            }
        }
        if (pattern.matcher(IP).matches()) {
            return IP;
        } else {
            logger.warn("IP is not valid.[IP={}]", IP);
            return "";
        }
    }
}
