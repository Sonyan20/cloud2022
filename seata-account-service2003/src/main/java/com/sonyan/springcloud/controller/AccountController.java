package com.sonyan.springcloud.controller;

import com.sonyan.springcloud.bean.CommonResult;
import com.sonyan.springcloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author sonyan
 * @create 2022/5/11 11:02
 */
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money")BigDecimal money){
        accountService.decrease(userId,money);

        return new CommonResult(200, "账户余额扣减成功！");
    }
}
