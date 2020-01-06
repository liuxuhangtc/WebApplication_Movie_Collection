package me.xuhang.movie.service;

import me.xuhang.movie.entity.Subject;
import me.xuhang.movie.rest.PageInfo;

/**
 * Created by xuhang on 2019/11/20.
 */
public interface SubjectService {

    public PageInfo<Subject> listBySearch(int pageNo, int pageSize, String year, String place, String type, String sort, String key);

}
