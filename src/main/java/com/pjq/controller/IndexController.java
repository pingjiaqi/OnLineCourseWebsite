//package com.pjq.Controller;
//
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Controller
//@RequestMapping(path = "/", method = RequestMethod.GET)
////在默认情况下springmvc的实例都是单例模式,所以使用scope域将其注解为每次都创建一个新的实例
//@Scope("prototype")
//public class IndexController {
//    //自动注入业务层的userService类
//    @RequestMapping(path = "/")
//    public String login(HttpServletRequest request) {
//        return "index";
//
//    }
//}
//
