package com.pjq.controller;


import com.pjq.pojo.Result;
import com.pjq.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping(path = "/home")
public class CommodityController {

    @Resource
    private CommodityService service;

    @Autowired
    CommodityService commodityService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public Result showAllCommodity(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        result.setResult(commodityService.showAllCommodity());
        result.setMessage("success");
        result.setCode("200");
        return result;
    }
}
