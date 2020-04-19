package com.pjq.controller;

import com.pjq.pojo.Result;
import com.pjq.service.LearningTimeService;
import com.pjq.service.HistoryService;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author pjq
 */
@RestController
@Scope("prototype")
@RequestMapping(path = "/api")
public class LearningTimeController {
    @Resource
    private LearningTimeService learningTimeService;

    @Resource
    private HistoryService historySerice;

    @RequestMapping(path = "/time", method = RequestMethod.POST)
    public Result showLearningTime(HttpSession httpSession) {
        String username = (String) httpSession.getAttribute("username");
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        result.setResult(learningTimeService.showLearningTime(username));
        return result;
    }

    @RequestMapping(path = "/addtime", method = RequestMethod.POST)
    public Result addLeariningTime(HttpSession httpSession, int time) {
        String username = (String) httpSession.getAttribute("username");
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        result.setResult(learningTimeService.addLeariningTime(username, time));
        return result;
    }

    @RequestMapping(path = "/history/add", method = RequestMethod.POST)
    public Result history(HttpSession httpSession,String lectureUrl){
        String userName=httpSession.getAttribute("username").toString();
        historySerice.history(userName,lectureUrl);
        return new Result("200","success",null);
    }

    @RequestMapping(path = "/history/show",method = RequestMethod.POST)
    public Result showHistories(HttpSession httpSession){
        String userName=httpSession.getAttribute("username").toString();
        return new Result("200","success",historySerice.showAllHistory(userName));
    }

}
