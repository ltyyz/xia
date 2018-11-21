package com.github.loutai.xia.controller;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.service.RolePermissionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/xia/rolePermission")
public class RolePermissionController {

    @Resource
    private RolePermissionService rolePermissionService;

    @RequestMapping("/list")
    public ServiceResult list(Integer roleId) {
        return rolePermissionService.listByRoleId(roleId);
    }

    @RequestMapping("/save")
    public ServiceResult save(Integer roleId, String permissionIds) {
        return rolePermissionService.save(roleId, permissionIds.split(","));
    }
}
