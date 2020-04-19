package com.pjq.service;

import com.pjq.dao.ChooseDao;
import com.pjq.dao.CourseDao;
import com.pjq.dao.MyCourseDao;
import com.pjq.dao.ShoppingCartDao;
import com.pjq.pojo.*;
import com.pjq.vo.CourseContentAndLectureVo;
import com.pjq.vo.CourseDescriptionVo;
import com.pjq.vo.MyCourseVo;
import com.pjq.vo.ShoppingCartVo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Scope("prototype")
public class CourseService {
    @Resource
    private CourseDao courseMapper;

    @Resource
    private MyCourseDao myCourseMapper;

    @Resource
    private UserService userService;

    @Resource
    private ShoppingCartDao shoppingCartMapper;

    @Resource
    private ChooseDao chooseMapper;

    public List<Course> getAllCourse() {
        return courseMapper.getAllCourse();
    }

    public List<Course> findCourse(String course_name, String content, String subject) {
        List<Course> courseName = courseMapper.selectByCourse(course_name, content, subject);
        return courseName;
    }

    public List<MyCourseVo> findMyCourse(String username) {
        List<MyCourse> myCourseList = new ArrayList<>();
        List<MyCourseVo> myCourseVoList = new ArrayList<>();
        myCourseList = myCourseMapper.findMyCourse(username);
        for (int j = 0; j < myCourseList.size(); j++) {
            MyCourse myCourse = myCourseList.get(j);
            int course_id = myCourse.getCourse_id();
            Course course = courseMapper.selectDetailCourse(course_id);
            String teacher = course.getTeacher();
            MyCourseVo myCourseVo = new MyCourseVo();
            myCourseVo.setCourseId(course_id);
            myCourseVo.setAddTime(myCourse.getAdd_time());
            myCourseVo.setCourseName(course.getCourse_name());
            myCourseVo.setProgress(myCourse.getProgress());
            myCourseVo.setTeacher(teacher);
            myCourseVo.setImgUrl(course.getCover_img());
            myCourseVoList.add(myCourseVo);
        }
        return myCourseVoList;
    }

    public Boolean deleteMyCourse(String username, int course_id) {
        Boolean type = courseMapper.deleteByUserCourse(username, course_id);
        return type;
    }

    public void insetIntoUserCourse(String username) {
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.selectShoppingCart(username);
        List<Integer> courseId = courseMapper.selectByUserCourse(username);
        for (ShoppingCart shoppingCart : shoppingCartList) {
            if (courseId.contains(shoppingCart.getProduct_id())) {
                continue;
            } else {
                courseMapper.insetIntoUserCourse(username,shoppingCart.getProduct_id());
                shoppingCartMapper.clearProduct(username,shoppingCart.getProduct_id());
            }
        }
    }


    public Course findDetailCourse(int course_id) {
        Course course = courseMapper.selectDetailCourse(course_id);
        return course;
    }

    public CourseDescriptionVo findCourseDescription(int course_id) {
        CourseDescriptionVo courseDescriptionVo = new CourseDescriptionVo();
        List<String> courseDescriptionLearns = new ArrayList<>();

        courseDescriptionLearns = courseMapper.selectCourseDescriptionLearn(course_id);
        courseDescriptionVo.setCourseId(course_id);
        courseDescriptionVo.setLearn(courseDescriptionLearns);

        Course course = new Course();
        course = courseMapper.selectDetailCourse(course_id);
        courseDescriptionVo.setDescription(course.getDescription());
        courseDescriptionVo.setCourseName(course.getCourse_name());
        courseDescriptionVo.setCoverImg(course.getCover_img());
        courseDescriptionVo.setPurchased(course.getPurchased());

        User teacher = userService.userMessage(course.getTeacher());
        courseDescriptionVo.setTeacherImg(teacher.getAvatar());

        List<String> courseDescriptionDemand = new ArrayList<>();
        courseDescriptionDemand = courseMapper.selectCourseDescriptionDemand(course_id);
        courseDescriptionVo.setDemand(courseDescriptionDemand);

        List<String> courseDescriptionAudience = new ArrayList<>();
        courseDescriptionAudience = courseMapper.selectCourseDerscriptionAudience(course_id);

        courseDescriptionVo.setAudience(courseDescriptionAudience);

        courseDescriptionVo.setTeacher(course.getTeacher());

        courseDescriptionVo.setCategory(course.getSubject());

        return courseDescriptionVo;
    }

    public List<CourseContentList> findCourseContentList(int course_id) {
        List<CourseContentList> courseContentLists = courseMapper.selectCourseContentList(course_id);
        Collections.sort(courseContentLists);
        return courseContentLists;
    }


    public List<CourseContentAndLectureVo> findLecture(int course_id) {
        List<Lecture> lectureList = courseMapper.selectCourseLecture(course_id);
        List<CourseContentList> courseContentLists = findCourseContentList(course_id);
        Collections.sort(courseContentLists);
        List<CourseContentAndLectureVo> courseContentAndLectureVos = new ArrayList<>();
        for (CourseContentList cL : courseContentLists) {
            CourseContentAndLectureVo courseContentAndLectureVo = new CourseContentAndLectureVo();
            List<Lecture> lectureList1 = new ArrayList<>();
            int time = 0;
            for (Lecture lecture : lectureList) {
                if (lecture.getContent_list_id() == cL.getId()) {
                    lectureList1.add(lecture);
                    time += lecture.getTimes();
                }
            }
            courseContentAndLectureVo.setAllTime(time);
            courseContentAndLectureVo.setLectureList(lectureList1);
            courseContentAndLectureVo.setLectureNumber(lectureList1.size());
            courseContentAndLectureVo.setCourse_content_list(cL.getCourse_content_list());
            courseContentAndLectureVos.add(courseContentAndLectureVo);
        }
        return courseContentAndLectureVos;
    }

    public List<Course> findMyUploadCourse(String userName){
        return courseMapper.selectCourse(userName);
    }

    public List<Choose> findCourseTest(int courseId){
        List<Choose> chooses=chooseMapper.selectTest(courseId);
        Collections.sort(chooses);
        return chooses;
    }

//    public List<Course> findTeachingCourses(String userName){
//
//    }
}
