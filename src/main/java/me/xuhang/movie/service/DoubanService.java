package me.xuhang.movie.service;

import org.json.JSONObject;

import me.xuhang.movie.entity.Subject;

import java.util.List;

/**
 * Created by xuhang on 2019/11/20.
 */
public interface DoubanService {

    public Subject find(String id);

    public Subject find(JSONObject jsonObject);

    public List<Subject> findPlaying();

    public void saveBySearch(String q);

}
