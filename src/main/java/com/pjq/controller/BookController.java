//package com.pjq.controller;
//
//
//import com.pjq.pojo.Book;
//import com.pjq.pojo.Result;
//import com.pjq.service.BookService;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//@Controller
//@Scope("prototype")
//@RequestMapping(path = "/api/book")
//@CrossOrigin
//public class BookController {
//    @Resource
//    BookService bookService;
//
//    @ResponseBody
//    @RequestMapping(path = "/findallbook", method = RequestMethod.GET)
//    public Result findAllBooks() {
//        Result result = new Result();
//        List<Book> bookList = bookService.getAllBooks();
//        result.setResult(bookList);
//        result.setCode("200");
//        result.setMessage("success");
//        return result;
//    }
//
//    @ResponseBody
//    @RequestMapping(path = "/my/findmybook", method = RequestMethod.GET)
//    public Result findMyBook(HttpSession session) {
//        String username = (String) session.getAttribute("username");
//        Result result = new Result();
//        result.setResult(bookService.showMyBooks(username));
//        result.setCode("200");
//        result.setMessage("success");
//        return result;
//    }
//
//    @ResponseBody
//    @RequestMapping(path = "/my/findAbook", method = RequestMethod.POST)
//    public Result findABook(HttpSession session, String title) {
//        Result result = new Result();
//        String username = (String) session.getAttribute("username");
//        result.setResult(bookService.findBook(username, title));
//        result.setMessage("success");
//        result.setCode("200");
//        return result;
//    }
//
//    @ResponseBody
//    @RequestMapping(path = "/my/buybook", method = RequestMethod.POST)
//    public Result buyBook(HttpSession session, String title) {
//        Result result = new Result();
//        String username = (String) session.getAttribute("username");
//        result.setCode("200");
//        if (bookService.buyABook(username, title) == false) {
//            result.setMessage("书本不存在");
//        } else {
//            result.setMessage("success");
//        }
//        return result;
//    }
//
//    @ResponseBody
//    @RequestMapping(path = "/my/deletebook", method = RequestMethod.POST)
//    public Result deleteBook(HttpSession session, String title) {
//        Result result = new Result();
//        String username = (String) session.getAttribute("username");
//        result.setCode("200");
//        result.setMessage(bookService.deleteMyBook(username, title));
//        return result;
//    }
//
//
//}
