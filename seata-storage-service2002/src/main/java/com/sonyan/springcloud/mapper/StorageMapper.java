package com.sonyan.springcloud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author sonyan
 * @create 2022/5/11 10:42
 */
@Mapper
public interface StorageMapper {
    /**
     * 减库存
     */
//    @Update("UPDATE `t_storage SET used = userd + #{count}, residue = residue - #{count} WHERE product_id = #{productId}`")
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
