package me.xuhang.movie.service.impl;

import me.xuhang.movie.dao.SubjectDao;
import me.xuhang.movie.entity.Subject;
import me.xuhang.movie.rest.PageInfo;
import me.xuhang.movie.service.BaseService;
import me.xuhang.movie.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuhang on 2019/11/20.
 */
@Service
public class SubjectServiceImpl extends BaseService implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Override
    public PageInfo<Subject> listBySearch(int pageNo, int pageSize, String year, String place, String type, String sort,String key) {
        PageInfo<Subject> pageInfo = new PageInfo<Subject>(pageNo, pageSize);
        pageInfo.setResultList(subjectDao.listBySearch(pageInfo.getStartRow(), pageSize, year, place, type, sort,key));
        pageInfo.setTotalRows(subjectDao.countBySearch(year, place, type, sort,key));
        return pageInfo;
    }

}
