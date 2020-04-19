package com.pjq.controller;


import com.pjq.pojo.Result;
import com.pjq.service.PostService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(path = "api/post")
@Scope("prototype")
public class PostController {

    @Autowired
    PostService postService;

    @RequestMapping(path = "/showallpost", method = RequestMethod.GET)
    public Result showAllPost() {
        Result result = new Result();
        result.setResult(postService.showAllPost());
        result.setMessage("success");
        result.setCode("200");
        return result;
    }

    @RequestMapping(path = "/showApost", method = RequestMethod.GET)
    public Result showAPost(@Param("post_id") int post_id) {
        return postService.showAPost(post_id);
    }

    @RequestMapping(path = "/showComments", method = RequestMethod.GET)
    public Result showComments(@Param("post_id") int post_id) {
        return postService.showComments(post_id);
    }

    @RequestMapping(path = "/my/addComments", method = RequestMethod.POST)
    public Result addCommetns(@Param("comments") String comments, HttpSession session, @Param("post_id") int post_id) {
        String name = (String) session.getAttribute("username");
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        postService.addComments(comments, name, post_id);
        return result;
    }

    @RequestMapping(path = "/my/addPost", method = RequestMethod.POST)
    public Result addPost(@Param("theme") String theme, @Param("content") String content, HttpSession session) {
        String name = (String) session.getAttribute("username");
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        postService.addPost(theme, content, name);
        return result;
    }


}
