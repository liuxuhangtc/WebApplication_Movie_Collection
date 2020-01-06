package me.xuhang.movie.service;

import java.util.List;

import me.xuhang.movie.entity.User;

/**
 * Created by xuhang on 2019/11/20.
 */
public interface UserService {

    public void create(User user);

    public List<User> findAll();
}
