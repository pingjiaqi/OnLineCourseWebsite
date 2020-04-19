package com.pjq.service;

import com.pjq.dao.ShoppingCartDao;
import com.pjq.pojo.ShoppingCart;
import com.pjq.vo.ShoppingCartVo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
public class ShoppingCartService {

    @Resource
    private ShoppingCartDao shoppingCartMapper;

    @Resource
    private CourseService courseService;

    public void addToShoppingCart(String username, int product_id, double price) {
        ShoppingCart shoppingCart = shoppingCartMapper.selectProduct(username, product_id);
        if (shoppingCart != null) {
            int number = shoppingCart.getNumber();
            number += 1;
            shoppingCartMapper.insertIntoDuplicates(username, product_id, number);
        } else {
            shoppingCartMapper.insertIntoShoppingCart(username, product_id, price);
        }

    }

    public List<ShoppingCartVo> showShoppingCart(String username) {
        List<ShoppingCartVo> shoppingCartVoList = new ArrayList<>();
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.selectShoppingCart(username);
        for (ShoppingCart shoppingCart : shoppingCartList) {
            String product_name = courseService.findDetailCourse(shoppingCart.getProduct_id()).getCourse_name();
            ShoppingCartVo shoppingCartVo = new ShoppingCartVo();
            shoppingCartVo.setProduct_name(product_name);
            shoppingCartVo.setNumber(shoppingCart.getNumber());
            shoppingCartVo.setPrice(shoppingCart.getPrice());
            shoppingCartVo.setUsername(shoppingCart.getUsername());
            shoppingCartVo.setProduct_id(shoppingCart.getProduct_id());
            shoppingCartVoList.add(shoppingCartVo);
        }
        return shoppingCartVoList;
    }

    public void cutProduct_name(String username, int product_id) {
        ShoppingCart shoppingCart = shoppingCartMapper.selectProduct(username, product_id);
        if (shoppingCart.getNumber() > 1) {
            int number = shoppingCart.getNumber() - 1;
            shoppingCartMapper.cutProduct(username, product_id, number);
        } else {
            shoppingCartMapper.clearProduct(username, product_id);
        }
    }

}
