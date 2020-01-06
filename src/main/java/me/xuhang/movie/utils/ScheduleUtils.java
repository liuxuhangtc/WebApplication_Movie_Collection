package me.xuhang.movie.utils;

import me.xuhang.movie.dao.PlayingDao;
import me.xuhang.movie.dao.SubjectDao;
import me.xuhang.movie.entity.Playing;
import me.xuhang.movie.service.DoubanService;
import me.xuhang.movie.service.impl.DoubanServiceImpl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhang on 2019/11/20.
 */
@SuppressWarnings("unused")
@Service
public class ScheduleUtils {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private PlayingDao playingDao;

    @Autowired
    private DoubanService doubanService;

    @Scheduled(cron = "0 0 0 * * ?")   //schedule at 12am everyday
    public void renewPlaying() {
        String id;
        List<Playing> playingList;
        String responseBody = subjectDao.findAllList();//HttpClientUtils.get(DoubanServiceImpl.DOUBAN_PLAYING_URL);
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray jsonArray = jsonObject.getJSONArray("entries");
        int length = jsonArray.length();
        logger.debug("{} movies are playing", length);
        playingList = new ArrayList<Playing>(length);
        for (int i = 0; i < length; i++) {
            id = jsonArray.getJSONObject(i).getString("id");
            doubanService.find(id);
            playingList.add(new Playing(id));
        }
        playingDao.renew(playingList);
    }
}
