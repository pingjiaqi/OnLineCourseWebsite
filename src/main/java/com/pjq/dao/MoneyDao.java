package com.pjq.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MoneyDao {

    void updateMoney(@Param("money") int money,@Param("userName") String userName);
}
