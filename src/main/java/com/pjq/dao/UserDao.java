package com.pjq.dao;

import com.pjq.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author pjq
 */
@Mapper
public interface UserDao {
    public User selectByName(String username);

    public void insertIntoUser(@Param("userName") String userName, @Param("passWord") String passWord,
                               @Param("name") String name);

    public void updateUserInformation(@Param("userName") String userName, @Param("studentId") String studentId,
                                      @Param("name") String name, @Param("sex") String sex, @Param("phone") String phone,
                                      @Param("description") String description, @Param("email") String email);

    void updateUserAvatar(@Param("url") String url,@Param("username")String username);

    void alertPassword(@Param("password") String password,@Param("username") String username);
}
