package com.pjq.dao;

import com.pjq.pojo.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HistoryDao {

    void insertIntoHistory(@Param("userName")String userName,@Param("lectureId")int lectureId);

    List<History> selectAllHistory(String userName);
}
