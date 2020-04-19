package com.pjq.dao;

import com.pjq.pojo.Choose;
import com.pjq.pojo.Course;
import com.pjq.pojo.CourseContentList;
import com.pjq.pojo.Lecture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author pjq
 */
@Mapper
public interface CourseDao {
    //查找所以课程
    List<Course> getAllCourse();

    //查找一个课程(可以模糊查找、条件查找）
    List<Course> selectByCourse(@Param("course_name") String course_name, @Param("content") String content,
                                       @Param("subject") String subject);

    //查找我的课程(全部）
    List<Integer> selectByUserCourse(String username);

    //查找一个课程
    Course selectDetailCourse(@Param("course_id") int course_id);

    //删除我的课程
    boolean deleteByUserCourse(@Param("username") String username, @Param("course_id") int course_id);

    //购买完成我的课程（添加课程)
    boolean insetIntoUserCourse(@Param("username") String username, @Param("course_id") int course_id);

    //课程进度调整
    boolean updateCourseProgress(@Param("username") String username, @Param("course_id") int course_id,
                                        @Param("progress") double progress);

    //查看进度
    double selectProgress(@Param("username") String username, @Param("course_id") int course_id);

    //查找关键字
    List<Course> findKeyWordCourse(@Param("keyword") String keyword);

    //查找课程学习目的
    List<String> selectCourseDescriptionLearn(@Param("course_id") int course_id);

    //查找课程的需求
    List<String> selectCourseDescriptionDemand(@Param("course_id") int course_id);

    //查找课程的受众
    List<String> selectCourseDerscriptionAudience(@Param("course_id") int course_id);

    //查找课程课时
    List<CourseContentList> selectCourseContentList(@Param("course_id") int course_id);

    CourseContentList selectCourseContent(@Param("course_id") int course_id,@Param("course_content_list") String cotentName);

    //查重
    Course checkCourse(@Param("course_name") String course_name,@Param("username")String username);

    //查找课程课时
    List<Lecture> selectCourseLecture(@Param("course_id") int course_id);

    //查找课程
    List<Course> selectCourse(String userName);

    //查找用户上传的课程
    List<Course> selectTeachingCourses(String userName);


}
