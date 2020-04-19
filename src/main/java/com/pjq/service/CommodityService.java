package com.pjq.service;

import com.pjq.dao.CommodityDao;
import com.pjq.pojo.Commodity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("commodity")
@Scope("prototype")
public class CommodityService {
    @Resource
    private CommodityDao commodityMapper;

    public List<Commodity> showAllCommodity() {
        return commodityMapper.showAllCommodity();
    }
}
