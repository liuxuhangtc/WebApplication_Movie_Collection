package me.xuhang.movie.utils;

import me.xuhang.movie.dao.SubjectDao;
import me.xuhang.movie.dao.UserDao;
import me.xuhang.movie.entity.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * for jstl
 * Created by xuhang on 2019/11/20.
 */
@Service
public class ApplicationUtils {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SubjectDao subjectDao;

    public String findUserNameById(String id) {
        return userDao.find(id).getUserName();
    }

    public Subject findSubjectById(String id) {
        return subjectDao.find(id);
    }

}
