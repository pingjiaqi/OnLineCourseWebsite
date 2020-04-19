package com.pjq.service;

import com.pjq.dao.CourseDao;
import com.pjq.dao.LectureDao;
import com.pjq.pojo.Course;
import com.pjq.pojo.Lecture;
import com.pjq.vo.CoursePlayerVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LectureService {

    @Resource
    LectureDao lectureDao;

    @Resource
    CourseDao courseDao;

    public CoursePlayerVo showLectureInfo(int lectureId){
        Lecture lecture=lectureDao.lectureInfo(lectureId);
        CoursePlayerVo coursePlayerVo=new CoursePlayerVo();
        coursePlayerVo.setVideoUrl(lecture.getUrl());
        coursePlayerVo.setLikeNumber(lecture.getLikes());
        return coursePlayerVo;
    }

    public Lecture findLecture(String lectureUrl){
        Lecture lecture=lectureDao.lecture(lectureUrl);
        return lecture;
    }

//    public String showVideoInfo(int courseId,int contentListId,int lectureId){
//        VideoInfoVo infoVo=new VideoInfoVo();
//        String videoUrl=videoDao.videoUrl(courseId,contentListId,lectureId);
//        return  videoUrl;
//    }
}
