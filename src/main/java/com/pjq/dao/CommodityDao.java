package com.pjq.dao;

import com.pjq.pojo.Commodity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommodityDao {
    //展示商品
    public List<Commodity> showAllCommodity();

}
