package com.pjq.controller;


import com.pjq.pojo.Result;
import com.pjq.pojo.User;
import com.pjq.service.CourseService;
import com.pjq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author pjq
 */
@RestController
@RequestMapping(value = "/user")
@Scope("prototype")
public class UserController {

    @Autowired
    UserService userService;

    @Resource
    private CourseService courseService;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public Result login(@RequestParam("username") String userName, @RequestParam("password") String passWord, HttpSession session) {
        Result result = new Result();
        Boolean t = userService.login(userName, passWord);
        if (t == true) {
            User user = userService.userMessage(userName);
            session.setAttribute("username", userName);
            session.setAttribute("type", user.getType());
            result.setMessage("success");
            result.setResult(user);
            result.setCode("200");
        } else {
            result.setMessage("fail");
        }
        return result;
    }


    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public Result registerIsWork(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        Result result = new Result();
        if (userService.register(username, password, name) == true) {
            result.setMessage("success");
        } else {
            result.setMessage("fail");
        }
        return result;
    }

    @RequestMapping(path = "/showinformation", method = RequestMethod.GET)
    public Result showInformation(HttpSession httpSession) {
        String username = (String) httpSession.getAttribute("username");
        User user = userService.userMessage(username);
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        result.setResult(user);
        return result;
    }

    @RequestMapping(path = "/changeinformation", method = RequestMethod.POST)
    public Result changeInformation(HttpSession httpSession, String sex, String student_id, String name, String email,
                                    String phone, String description) {
        String username = (String) httpSession.getAttribute("username");
        userService.changeInformation(username, student_id, name, sex, phone, description, email);
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        return result;
    }

    @RequestMapping(path = "/unLogin", method = RequestMethod.GET)
    public Result unLogin() {
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        result.setResult("intercept");
        return result;
    }

    @RequestMapping(path = "/logOut", method = RequestMethod.POST)
    public Result logOut(HttpSession session) {
        session.setAttribute("username",null);
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        return result;
    }

    @RequestMapping(path = "/alertPassword",method = RequestMethod.POST)
    public Result alertPassword(String password,HttpSession httpSession){
        String userName= (String) httpSession.getAttribute("username");
        userService.alertPassword(password,userName);
        Result result=new Result();
        result.setResult("success");
        result.setCode("200");
        result.setMessage("success");
        return result;
    }

    @RequestMapping(path = "/teacher",method = RequestMethod.POST)
    public Result showMyUploadCourse(HttpSession httpSession){
        String userName=httpSession.getAttribute("username").toString();
        Result result=new Result("200","success",courseService.findMyUploadCourse(userName));
        return result;
    }

}
