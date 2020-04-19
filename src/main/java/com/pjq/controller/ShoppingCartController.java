package com.pjq.controller;


import com.pjq.pojo.Course;
import com.pjq.pojo.Result;
import com.pjq.service.CourseService;
import com.pjq.service.ShoppingCartService;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@Scope("prototype")
@RequestMapping(path = "/api/shoppingcart")
public class ShoppingCartController {

    @Resource
    private CourseService courseService;
    @Resource
    private ShoppingCartService shoppingCartService;


    @RequestMapping(path = "/addtoshoppingcart", method = RequestMethod.POST)
    public Result addToShoppingCart(String product_id, HttpSession httpSession) {
        int productId= Integer.parseInt(product_id);
        Result result = new Result();
        String username = (String) httpSession.getAttribute("username");
        Course course = courseService.findDetailCourse(productId);
        if (course == null) {
            result.setCode("200");
            result.setMessage("null");
            return result;
        }
        double price = course.getPrice();
        shoppingCartService.addToShoppingCart(username, course.getId(), price);
        result.setCode("200");
        result.setMessage("success");
        return result;
    }

    @RequestMapping(path = "/showshoppingcart", method = RequestMethod.GET)
    public Result showMyShoppingCart(HttpSession httpSession) {
        String username = (String) httpSession.getAttribute("username");
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        result.setResult(shoppingCartService.showShoppingCart(username));
        return result;
    }

    @RequestMapping(path = "/cutproduct", method = RequestMethod.POST)
    public Result cutShoppingCart(int product_id, HttpSession httpSession) {
        String username = httpSession.getAttribute("username").toString();
        shoppingCartService.cutProduct_name(username, product_id);
        Result result = new Result();
        result.setCode("200");
        result.setMessage("success");
        return result;
    }


}
