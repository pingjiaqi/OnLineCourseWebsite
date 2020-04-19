package com.pjq.controller;

import com.pjq.pojo.Result;
import com.pjq.service.FriendsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@Scope("prototype")
@RequestMapping(path = "/api/friends")
public class FriendsController {
    @Resource
    FriendsService friendsService;

    @RequestMapping(path = "/my/findfriends", method = RequestMethod.POST)
    public Result findFriends(HttpSession session, String friends_name) {
        String username = (String) session.getAttribute("username");
        Result result = new Result();
        if (friendsService.selectUserFriends(username, friends_name) != null) {
            result.setCode("200");
            result.setMessage("success");
            result.setResult(friendsService.selectUserFriends(username, friends_name));
        } else {
            result.setCode("404");
            result.setMessage("fail");
        }
        return result;
    }

    @RequestMapping(path = "/my/requestfriends", method = RequestMethod.POST)
    public Result requestFriends(HttpSession session, String friends_name) {
        String username = (String) session.getAttribute("username");
        Result result = new Result();
        result.setCode("200");
        result.setMessage(friendsService.insertIntoFriend(username, friends_name));
        return result;
    }


    @RequestMapping(path = "/my/responsefriends", method = RequestMethod.POST)
    public Result responseFriends(HttpSession session, @Param("friends_name") String friends_name,
                                  @Param("isagreed") String isagreed) {
        String username = (String) session.getAttribute("username");
        Result result = new Result();
        result.setCode("200");
        String message = "agreed";
        if (isagreed != message) {
            result.setMessage(friendsService.responseInsertIntoFriends(username, friends_name));
        } else {
            friendsService.rejectInsertIntoFriends(username, friends_name);
            result.setMessage("拒绝成功");
        }
        return result;
    }

    @RequestMapping(path = "/my/deletefriends", method = RequestMethod.POST)
    public Result deleteFriends(HttpSession session, String friends_name) {
        String username = (String) session.getAttribute("username");
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        friendsService.deleteByUserFriend(username, friends_name);
        return result;
    }


}
