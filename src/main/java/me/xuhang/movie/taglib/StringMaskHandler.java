package me.xuhang.movie.taglib;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by xuhang on 2019/11/20.
 */
public class StringMaskHandler extends SimpleTagSupport {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());


    String bM;

    int start = 1;

    int length = 0;

    /**
     * @throws JspException
     * @throws IOException
     */
    @Override
    public void doTag() throws JspException, IOException {
        if (StringUtils.isBlank(bM)) {
            logger.warn("Invalid beforeMask.");
            return;
        }
        JspWriter writer = getJspContext().getOut();
        if (length <= 0) {
            length = bM.length() == 2 ? 1 : bM.length() - 2;
        }
        if (length > bM.length() - 1) {
            length = bM.length() - 1;
        }
        writer.write(mask(bM, start, length));
    }

    public void setBeforeMask(String beforeMask) {
        this.bM = beforeMask.trim();
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setLength(int length) {
        this.length = length;
    }

    private static char[] mask(String content, int offset, int length) {
        char[] chars = content.toCharArray();
        for (int i = offset; i < offset + length; i++) {
            chars[i] = '*';
        }
        return chars;
    }

}
