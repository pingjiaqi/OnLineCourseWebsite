package com.pjq.controller;

import com.pjq.pojo.Result;
import com.pjq.service.FileService;
import com.pjq.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;


/**
 * @author pjq
 */
@RestController
@RequestMapping(path = "/api/file")
public class FileController {

    @Resource
    UserService userService;

    @Resource
    FileService fileService;

    @RequestMapping(path = "/img/upload", method = RequestMethod.POST)
    public Result upLoadImage(@Param("file") MultipartFile file, HttpSession httpSession) {
        String username = httpSession.getAttribute("username").toString();
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = "user_" + username + suffix;
        String path = "C:\\Users\\13457\\Desktop\\resources\\images\\";
        File newFile = new File(path + newFileName);
        String imgUrl = "http://127.0.0.1:8089/" + newFileName;
        userService.updateAvatar(imgUrl, username);
        String avatarUrl = userService.userMessage(username).getAvatar();
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        try {
            file.transferTo(newFile);
            result.setResult(avatarUrl);
        } catch (Exception e) {
            e.printStackTrace();
            result.setResult("fail");
        }
        return result;
    }

    @RequestMapping(path = "/courseInfo/upload", method = RequestMethod.POST)
    public Result upLoadCourseInfo(@Param("file") MultipartFile file, HttpSession httpSession) {
        String userName = httpSession.getAttribute("username").toString();
        String fileName = file.getOriginalFilename();
        String isSuccess = fileService.analyzeExcel(userName, fileName, file);
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        result.setResult(isSuccess);
        return result;
    }

    @RequestMapping(path = "/courseInfo/video", method = RequestMethod.POST)
    public Result upLoadCourseVideo(@Param("file") MultipartFile file, HttpSession httpSession) {
        String userName = (String) httpSession.getAttribute("username");
        Result result=new Result();
        result.setResult(fileService.upLoadCourseVideo(userName,file));
        return result;
    }

    @RequestMapping(path = "/homeWork",method = RequestMethod.POST)
    public Result upLoadHomeWork(@Param("file") MultipartFile file,int courseId,HttpSession httpSession){
        String userName=httpSession.getAttribute("username").toString();
        Result result=new Result("200","success",fileService.upLoadHomeWork(file,courseId,userName));
        return result;
    }


}
