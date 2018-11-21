package com.github.loutai.xia.controller;

import com.github.loutai.xia.common.ServiceResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @RequestMapping("/needLogin")
    public Object login() {
        return ServiceResult.of("0401", "请登录",null);
    }

    @RequestMapping("/403")
    public Object ERROR403() {
        return ServiceResult.of("0403", "无权限", null);
    }

}
