package com.github.loutai.xia.controller;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.service.RoleMenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/xia/roleMenu")
public class RoleMenuController {

    @Resource
    private RoleMenuService roleMenuService;

    @RequestMapping("/list")
    public ServiceResult list(Integer roleId) {
        return roleMenuService.listByRoleId(roleId);
    }

    @RequestMapping("/save")
    public ServiceResult save(Integer roleId, String menuIds) {
        return roleMenuService.save(roleId, menuIds.split(","));
    }
}
