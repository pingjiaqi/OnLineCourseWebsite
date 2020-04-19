package com.pjq.dao;

import com.pjq.pojo.LearningTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author pjq
 */
@Mapper
public interface LearningTimeDao {

    public LearningTime selectTime(@Param("username") String username);

    public void updateTime(@Param("username") String username, @Param("time") int time, @Param("day") String day);

    void timeClear(@Param("username") String username);
}
