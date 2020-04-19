package com.pjq.controller;

import com.pjq.pojo.Result;
import com.pjq.service.SearchResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Scope("prototype")
@RequestMapping(value = "/api")
public class SearchResultController {

    @Autowired
    private SearchResultService searchResultService;

    @RequestMapping(path = "/searchKeyWord", method = RequestMethod.POST)
    public Result searchKeyWord(@RequestParam("keyword") String keyword, HttpSession session) {
        Result result = new Result();
        result = searchResultService.searchKeyWord(keyword);
        result.setMessage("success");
        result.setCode("200");
        return result;
    }


//    @RequestMapping(path = "/test",method = RequestMethod.GET)
//    public Result test(HttpSession session){
//        Result result=new Result();
//        result=searchResultService.searchKeyWord(keyword);
//        result.setMessage("success");
//        result.setCode("200");
//        return result;
//    }
}
