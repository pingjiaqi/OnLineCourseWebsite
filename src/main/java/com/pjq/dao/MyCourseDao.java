package com.pjq.dao;

import com.pjq.pojo.MyCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyCourseDao {
    List<MyCourse> findMyCourse(@Param("username") String username);
}
