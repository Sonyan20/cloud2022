package com.sonyan.springcloud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author sonyan
 * @create 2022/5/11 11:08
 */
@Mapper
public interface AccountMapper {

    /**
     * 扣减账户余额
     */
    void decrease(@Param("userId") Long userId, @Param("money")BigDecimal money);
}
