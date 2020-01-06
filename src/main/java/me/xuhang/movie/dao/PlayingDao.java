package me.xuhang.movie.dao;

import org.springframework.stereotype.Repository;

import me.xuhang.movie.entity.Playing;

import java.util.List;

/**
 * Created by xuhang on 2019/11/20.
 */
@Repository
public class PlayingDao extends BaseDao<Playing> {

    public PlayingDao() {
        super(Playing.class);
    }

    public void renew(List<Playing> playingList) {
        deleteAll();
        for (Playing playing : playingList) {
            create(playing);
        }
    }

}
