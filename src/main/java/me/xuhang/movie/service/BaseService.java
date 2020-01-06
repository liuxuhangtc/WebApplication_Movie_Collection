package me.xuhang.movie.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xuhang on 2019/11/20.
 */
public abstract class BaseService {

    protected Logger logger = null;

    public BaseService() {
        logger = LoggerFactory.getLogger(getClass().getName());
    }
}
