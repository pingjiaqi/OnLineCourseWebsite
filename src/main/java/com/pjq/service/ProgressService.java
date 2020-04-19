package com.pjq.service;


import com.pjq.dao.CourseDao;
import com.pjq.dao.HistoryDao;
import com.pjq.dao.LectureDao;
import com.pjq.pojo.History;
import com.pjq.pojo.Lecture;
import com.pjq.vo.CourseContentAndLectureVo;
import com.pjq.vo.MyCourseVo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("progressService")
@Scope("prototype")
public class ProgressService {

//    @Resource
//    private BookDao bookMapper;

    @Resource
    private CourseDao courseMapper;

    @Resource
    private CourseService courseService;

    @Resource
    private LectureDao lectureMapper;

    @Resource
    private HistoryDao historyMapper;

//    public boolean updateBookProgress(String username, String title, double progress) {
//        Book book = bookMapper.selectByUserBook(username, title);
//        if (book == null) {
//            return false;
//        }
//        return bookMapper.updateProgress(username, title, progress);
//    }

    public boolean updateCourseProgress(String userName, int courseId, String lectureUrl) {
        Lecture lecture=lectureMapper.lecture(lectureUrl);
        List<History> histories=historyMapper.selectAllHistory(userName);
        List<MyCourseVo> myCourses=courseService.findMyCourse(userName);
        List<CourseContentAndLectureVo> courseContentAndLectureVos=courseService.findLecture(courseId);
        double allTime = 0;
        for(CourseContentAndLectureVo  courseContentAndLectureVo:courseContentAndLectureVos){
            allTime+=courseContentAndLectureVo.getAllTime();
        }
        Double lastProgress=0.0;
        for(MyCourseVo myCourseVo:myCourses){
            if(myCourseVo.getCourseId()==courseId){
                lastProgress=myCourseVo.getProgress();
            }
        }
        for(History history:histories){
            if(history.getLecture_id()!=lecture.getId()){
                 lastProgress+=lecture.getTimes()*100*1.0/allTime;
                 if(lastProgress>100){
                     lastProgress=100.0;
                 }
            }
        }
        courseMapper.updateCourseProgress(userName, courseId, lastProgress);
        return true;
    }

    public double selectCourseProgress(String username, int course_id) {
        return courseMapper.selectProgress(username, course_id);
    }

}
