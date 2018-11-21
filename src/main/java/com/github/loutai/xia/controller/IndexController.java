package com.github.loutai.xia.controller;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaUser;
import com.github.loutai.xia.security.SecurityContextUtil;
import com.github.loutai.xia.service.IndexService;
import com.github.loutai.xia.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/xia/index")
public class IndexController {

    @Resource
    private IndexService indexService;

    @Resource
    private UserService userService;

    @Resource
    private SecurityContextUtil securityContextUtil;

    @RequestMapping("/applications")
    public ServiceResult applications() {
        return indexService.getCurrentUserApplications();
    }

    @RequestMapping("/menus")
    public ServiceResult menus(Integer appId) {
        return indexService.getCurrentUserMenus(appId);
    }

    @RequestMapping("/currentUser")
    public ServiceResult currentUser() {
        XiaUser user = securityContextUtil.getCurrentUser();
        user.setPassword(null);
        return ServiceResult.ofSuccess(user);
    }

    @RequestMapping("/updateUser")
    public ServiceResult updateUser(XiaUser user, String newPassword) {
        return userService.modifyUser(user, newPassword);
    }

    @RequestMapping("/switchDefaultApp")
    public ServiceResult switchDefaultApp(Integer appId) {
        return indexService.switchDefaultApp(appId);
    }
}
