package com.pjq.service;

import com.pjq.dao.MoneyDao;
import com.pjq.dao.UserDao;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MoneyService {

    @Resource
    private UserDao userMapper;

    @Resource
    private MoneyDao moneyMapper;

    public void addMoney(int money,String userName){
        int lastMoney=userMapper.selectByName(userName).getMoney();
        money+=lastMoney;
        moneyMapper.updateMoney(money,userName);
    }

}
