package com.pjq.controller;

import com.pjq.pojo.Result;
import com.pjq.service.MoneyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(path = "/api/money")
public class MoneyController {


    @Resource
    private MoneyService moneyService;

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public Result addMoney(int money, HttpSession httpSession){
        String userName=httpSession.getAttribute("username").toString();
        moneyService.addMoney(money,userName);
        Result result=new Result("200","success","success");
        return result;
    }


}
