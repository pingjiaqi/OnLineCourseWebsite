package com.pjq.dao;

import com.pjq.pojo.Friends;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FriendsDao {
    //显示好友
    List<Friends> getAllFriends(String username);

    //查找好友
    public Friends selectUserFriends(@Param("username") String username, @Param("friends_name") String friends_name);

    //请求添加好友
    public boolean insertIntoFriend(@Param("username") String username, @Param("friends_name") String friends_name);

    //同意添加好友
    public boolean agreedInsertIntoFriend(@Param("username") String username, @Param("friends_name") String friends_name);

    public boolean responseInsertIntoFriend(@Param("username") String friends_name, @Param("friends_name") String username);

    //拒绝
    public boolean rejectInsertIntoFriend(@Param("username") String friends_name, @Param("friends_name") String username);

    //删除好友
    public boolean deleteByUserFriend(@Param("username") String username, @Param("friends_name") String friends_name);

    //被删除
    public boolean isDeleted(@Param("username") String username, @Param("friends_name") String friends_name);
}
