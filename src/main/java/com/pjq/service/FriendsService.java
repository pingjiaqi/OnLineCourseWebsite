package com.pjq.service;


import com.pjq.dao.FriendsDao;
import com.pjq.dao.UserDao;
import com.pjq.pojo.Friends;

import com.pjq.pojo.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("friendsService")
@Scope("prototype")
public class FriendsService {
    @Resource
    FriendsDao friendsMapper;
    UserDao userMapper;

    public List<Friends> getAllFriends(String username) {

        return friendsMapper.getAllFriends(username);
    }

    public Friends selectUserFriends(String username, String friends_name) {

        Friends friends = friendsMapper.selectUserFriends(username, friends_name);
        return friends;
    }

    public String insertIntoFriend(String username, String friends_name) {

        User user = userMapper.selectByName(friends_name);
        Friends friends = friendsMapper.selectUserFriends(username, friends_name);
        String result;
        if (user == null) {
            result = "用户不存在";
        } else if (friends != null) {
            result = "已经是你的好友";

        } else {
            Boolean isInsert = friendsMapper.insertIntoFriend(username, friends_name);
            if (isInsert == true) {
                result = "好友请求成功";
            } else {
                result = "好友请求失败";
            }
        }
        return result;
    }

    public String responseInsertIntoFriends(String username, String friends_name) {

        String result;
        boolean agreed = friendsMapper.agreedInsertIntoFriend(username, friends_name);
        boolean respones = friendsMapper.responseInsertIntoFriend(friends_name, username);
        if (agreed == true && respones == true) {
            result = "添加成功";
        } else {
            result = "添加失败";
        }
        return result;
    }

    public void rejectInsertIntoFriends(String username, String friends_name) {

        friendsMapper.rejectInsertIntoFriend(friends_name, username);
    }

    public boolean deleteByUserFriend(String username, String friends_name) {

        boolean isDelete = friendsMapper.deleteByUserFriend(username, friends_name);
        //被删除
        boolean Delete = friendsMapper.isDeleted(username, friends_name);
        return isDelete;
    }
}
