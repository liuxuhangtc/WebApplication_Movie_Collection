package me.xuhang.movie.service.impl;

import me.xuhang.movie.dao.UserDao;
import me.xuhang.movie.entity.User;
import me.xuhang.movie.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by xuhang on 2019/11/20.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
