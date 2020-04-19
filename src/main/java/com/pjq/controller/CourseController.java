package com.pjq.controller;

import com.pjq.pojo.Course;
import com.pjq.pojo.Result;
import com.pjq.service.CourseService;
import com.pjq.service.LectureService;
import com.pjq.service.NotesService;
import com.pjq.vo.MyCourseVo;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author pjq
 */
@CrossOrigin
@RestController
@Scope("prototype")
@RequestMapping(path = "/api/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @Resource
    LectureService lectureService;

    @Resource
    NotesService notesService;


    @RequestMapping(path = "/findallcourse", method = RequestMethod.GET)
    public Result findAllCourse(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        Result result = new Result();
        List<Course> allCourse = courseService.getAllCourse();
        result.setMessage("success");
        result.setResult(allCourse);
        return result;
    }


    @RequestMapping(path = "/findcourse", method = RequestMethod.POST)
    public Result findCourse(HttpServletResponse response, @Param("course_name") String course_name, @Param("content") String content, @Param("subject") String subject) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        Result result = new Result();
        List<Course> course = courseService.findCourse(course_name, content, subject);
        result.setResult(course);
        if (course == null) {
            result.setMessage("fail");
        } else {
            result.setMessage("success");
        }
        return result;
    }

    @RequestMapping(path = "/findTeacherCourse",method = RequestMethod.POST)
    public Result findMyUploadCourse(HttpSession httpSession){
        String userName=httpSession.getAttribute("username").toString();
        Result result=new Result("200","success",courseService.findMyUploadCourse(userName));
        return  result;
    }


    @RequestMapping(path = "/my/findmycourse", method = RequestMethod.POST)
    public Result findMyCourse(HttpSession session, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String username = (String) session.getAttribute("username");
        Result result = new Result();
        List<MyCourseVo> course = courseService.findMyCourse(username);
        if (course == null) {
            result.setCode("500");
            result.setMessage("fail");
        } else {
            result.setCode("200");
            result.setResult(course);
            result.setMessage("success");
        }
        return result;
    }


    @RequestMapping(path = "/my/deletemycourse", method = RequestMethod.POST)
    public Result deleteMyCourse(HttpSession session, int course_id) {
        Result result = new Result();
        String username = (String) session.getAttribute("username");
        if (courseService.deleteMyCourse(username, course_id) == true) {
            result.setMessage("success");
        } else {
            result.setMessage("fail");
        }
        return result;
    }


    @RequestMapping(path = "/my/buycourse",method = RequestMethod.POST)
    public Result buyCourse(HttpSession session,boolean isSettle){
        Result result=new Result();
        String username=(String)session.getAttribute("username");
        if(isSettle==true){
            courseService.insetIntoUserCourse(username);
        }
        result.setCode("200");
        result.setMessage("success");
        return result;
    }

    @RequestMapping(path = "/description", method = RequestMethod.POST)
    public Result showCourseDescription(int course_id) {
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        result.setResult(courseService.findCourseDescription(course_id));
        return result;
    }

    @RequestMapping(path = "/contentlist", method = RequestMethod.POST)
    public Result showCourseContentList(String course_id) {
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        result.setResult(courseService.findLecture(Integer.parseInt(course_id)));
        return result;
    }

    @RequestMapping(path = "my/lecture", method = RequestMethod.POST)
    public Result showLecture(int course_id) {
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        result.setResult(courseService.findLecture(course_id));
        return result;
    }

    @RequestMapping(path = "/player",method = RequestMethod.POST)
    public Result showVideoUrl(int lectureId){
        Result result=new Result();
        result.setCode("200");
        result.setMessage("success");
        result.setResult(lectureService.showLectureInfo(lectureId));
        return result;
    }

    @RequestMapping(path = "/notes",method = RequestMethod.POST)
    public Result showNotes(int courseId){
        Result result=new Result();
        result.setCode("200");
        result.setMessage("success");
        result.setResult(notesService.showAllNotes(courseId));
        return result;
    }

    @RequestMapping(path = "/test",method = RequestMethod.POST)
    public Result showTest(int courseId){
        return new Result("200","success",courseService.findCourseTest(courseId));
    }


//    @RequestMapping(path = "/teaching", method= RequestMethod.POST)
//    public Result showTeachingCourses(HttpSession httpSession){
//        String userName= (String) httpSession.getAttribute("username");
//
//    }
}
