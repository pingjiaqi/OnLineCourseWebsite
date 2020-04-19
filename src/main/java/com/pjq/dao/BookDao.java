package com.pjq.dao;

import com.pjq.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookDao {
//    //查找所以的书
//    List<Book> getAllBooks();
//
//    //找一本书
//    public Book findabook(String title);
//
//    //展示我全部的书
//    public Book showUserBook(String username);
//
//    //查找我的书
//    public Book selectByUserBook(@Param("username") String username, @Param("title") String title);
//
//    //删除我的书
//    public boolean deleteByUserBook(@Param("username") String username, @Param("title") String title);
//
//    //查看进度
//    public double lookOverProgress(@Param("username") String username, @Param("title") String title);
//
//    //更新进度
//    public boolean updateProgress(@Param("username") String username, @Param("title") String title, @Param("progress") double progress);
//
//    //买书
//    public boolean insertIntoBook(@Param("username") String username, @Param("title") String title);
}
