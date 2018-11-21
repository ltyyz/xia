package com.github.loutai.xia.controller;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.service.UserRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/xia/userRole")
public class UserRoleController {
    
    @Resource
    private UserRoleService userRoleService;

    @RequestMapping("/list")
    public ServiceResult list(Integer userId) {
        return userRoleService.listByUserId(userId);
    }

    @RequestMapping("/save")
    public ServiceResult save(Integer userId, String roleIds) {
        return userRoleService.save(userId, roleIds.split(","));
    }
}
