package com.pjq.dao;

import com.pjq.pojo.Lecture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author pjq
 */
@Mapper
public interface LectureDao {
    Lecture lectureInfo(@Param("lectureId") int lectureId);

    Lecture lecture(@Param("lectureUrl")String lectureUrl);
}
