package com.sonyan.springcloud.mapper;

import com.sonyan.springcloud.bean.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author sonyan
 * @create 2022/5/10 16:34
 */
@Mapper
public interface OrderMapper {

    /**
     * 创建订单
     */
    void create(Order order);

    /**
     * 修改订单状态
     */
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
