package com.pjq.dao;

import com.pjq.pojo.Comments;
import com.pjq.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostDao {

    //全部的帖子
    public List<Post> getAllPost();

    //展示一个帖子
    public Post getAPost(int id);

    public List<Comments> getComents(int post_id);

    //发表评论
    public void insertIntoComments(@Param("post_id") int post_id, @Param("comments") String comments, @Param("name") String name);

    public void updateCommentsNumber(@Param("id") int id, @Param("number") double number);

    //发表帖子
    public void insertIntoPost(@Param("theme") String theme, @Param("content") String content, @Param("blogger") String blogger);

    //查找关键字
    public List<Post> findKeyWordPost(String keyword);
}
