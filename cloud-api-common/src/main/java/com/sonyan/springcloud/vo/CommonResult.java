package com.sonyan.springcloud.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sonyan
 * @create 2022/4/11 11:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {


    private Integer code;
    private String msg;
    private T data;

    public CommonResult(Integer code,String msg){
        this(code,msg,null);
    }
}
