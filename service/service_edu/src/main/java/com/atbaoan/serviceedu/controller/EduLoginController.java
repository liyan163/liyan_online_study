package com.atbaoan.serviceedu.controller;

import com.atbaoan.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author Emma
 * @create 2021-04-15 23:15
 * @description
 */
@RestController
@CrossOrigin
@Api("登录接口，写死的，没有调用数据库")
@RequestMapping("/user")
public class EduLoginController {


    @PostMapping("login")
    @ApiOperation(value = "登录接口")
    public R login(){
        return R.ok().data("token","data");
    }

    @GetMapping("/info")
    @ApiOperation(value = "展示信息")
    public R info(){
        return R.ok().data("roles","admin").data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
