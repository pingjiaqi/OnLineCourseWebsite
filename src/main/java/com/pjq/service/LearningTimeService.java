package com.pjq.service;

import com.pjq.dao.LearningTimeDao;
import com.pjq.pojo.LearningTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * @author pjq
 */
@Service
@Scope("prototype")
public class LearningTimeService {
    @Resource
    private LearningTimeDao learningTimeMapper;

    private String[] strings = new String[]{"monday", "tuesday", "wednesday", "thursday", "friday"};

    private String tempUrl = null;

    public LearningTime showLearningTime(String username) {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 2) {
            learningTimeMapper.timeClear(username);
        }
        return learningTimeMapper.selectTime(username);
    }

    public boolean addLeariningTime(String username, int time) {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        String week = null;
        if (weekday > 1 && weekday < 7) {
            week = strings[weekday - 2];
        } else {
            return true;
        }
        LearningTime learningTime = learningTimeMapper.selectTime(username);
        if (weekday == 2) {
            time += learningTime.getMonday();
        }
        if (weekday == 3) {
            time += learningTime.getTuesday();
        }
        if (weekday == 4) {
            time += learningTime.getWednesday();
        }
        if (weekday == 5) {
            time += learningTime.getThursday();
        }
        if (weekday == 6) {
            time += learningTime.getFriday();
        }
        learningTimeMapper.updateTime(username, time, week);
        return true;
    }
}
