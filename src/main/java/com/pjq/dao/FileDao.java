package com.pjq.dao;

import com.pjq.pojo.Course;
import com.pjq.pojo.CourseContentList;
import com.pjq.pojo.Lecture;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileDao {
    void upLoadCourseInfo(Course course);

    void upLoadCourseContentList(CourseContentList courseContentList);

    void upLoadLecture(Lecture lecture);
}
