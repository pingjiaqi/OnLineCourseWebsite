package com.pjq.dao;

import com.pjq.pojo.Choose;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 13457
 */
@Mapper
public interface ChooseDao {

    void insertIntoChoose(@Param("chooseList") List<Choose> chooseList);

    List<Choose> selectTest(int courseId);
}
