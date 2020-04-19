package com.pjq.dao;

import com.pjq.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShoppingCartDao {
    //加入购物车
    public void insertIntoShoppingCart(@Param("username") String username, @Param("product_id") int product_id, @Param("price") double price);

    //展示购物车
    public List<ShoppingCart> selectShoppingCart(@Param("username") String username);

    //加入重复商品
    public void insertIntoDuplicates(@Param("username") String username, @Param("product_id") int product_id, @Param("number") int number);

    //查找我的购物车
    public ShoppingCart selectProduct(@Param("username") String username, @Param("product_id") int product_id);

    //减少同款商品数量
    public void cutProduct(@Param("username") String username, @Param("product_id") int product_id, @Param("number") int number);

    void clearProduct(@Param("username")String username,@Param("product_id") int productId);
}
