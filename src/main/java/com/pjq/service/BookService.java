//package com.pjq.service;
//
//import com.pjq.dao.BookDao;
//import com.pjq.pojo.Book;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@Service("bookService")
//@Scope("prototype")
//public class BookService {
//    @Resource
//    BookDao bookMapper;
//
//    public List<Book> getAllBooks() {
//        return bookMapper.getAllBooks();
//    }
//
//    public Book showMyBooks(String username) {
//        Book book = bookMapper.showUserBook(username);
//        return book;
//    }
//
//    public Book findBook(String username, String title) {
//        Book book = bookMapper.selectByUserBook(username, title);
//        return book;
//    }
//
//    public String deleteMyBook(String username, String title) {
//        Book book = bookMapper.selectByUserBook(username, title);
//        if (book != null) {
//            bookMapper.deleteByUserBook(username, title);
//            return "success";
//        } else {
//            return "书本不存在";
//        }
//    }
//
//    public double lookOverProgress(String username, String title) {
//        Book book = bookMapper.selectByUserBook(username, title);
//        if (book == null) {
//            return 0;
//        } else {
//            double progress = bookMapper.lookOverProgress(username, title);
//            return progress;
//        }
//    }
//
//    public boolean updateProgress(String username, String title, double progress) {
//        Book book = bookMapper.selectByUserBook(username, title);
//        if (book == null) {
//            return false;
//        } else {
//            bookMapper.updateProgress(username, title, progress);
//            return true;
//        }
//    }
//
//    public boolean buyABook(String username, String title) {
//        Book book = bookMapper.findabook(title);
//        if (book == null) {
//            return false;
//        }
//        return bookMapper.insertIntoBook(username, title);
//    }
//
//
//}
