package com.sonyan.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author sonyan
 * @create 2022/5/11 10:19
 */
@Configuration
@MapperScan({"com.sonyan.springcloud.mapper"})
public class MybatisConfig {

}
