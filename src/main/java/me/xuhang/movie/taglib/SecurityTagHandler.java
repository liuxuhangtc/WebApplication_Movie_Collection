package me.xuhang.movie.taglib;

import lombok.Setter;
import me.xuhang.movie.entity.User;
import me.xuhang.movie.utils.ContextUtils;
import me.xuhang.movie.utils.SessionUtils;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * JSP tag support
 * Created by xuhang on 2019/11/20.
 */
public class SecurityTagHandler extends SimpleTagSupport {


    @Setter
    private String userId;

    @Override
    public void doTag() throws JspException, IOException {
        SessionUtils sessionUtils = getSessionUtils();
        if (null == sessionUtils) {
            return;
        }
        User currentUser = sessionUtils.getUser();
        if (null == currentUser) {
            return;
        }
        if (currentUser.isAdmin() || currentUser.getId().equals(userId)) {
            getJspBody().invoke(null);
        }
        return;
    }


    /**
     * Get SessionUtils
     *
     * @return
     */
    private SessionUtils getSessionUtils() {
        JspContext jc = getJspContext();
        Object sessionUtils = jc.getAttribute(ContextUtils.SESSIONUTILS, PageContext.SESSION_SCOPE);
        if (sessionUtils != null && sessionUtils instanceof SessionUtils) {
            return (SessionUtils) sessionUtils;
        }
        return null;
    }

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}
    
    
}
