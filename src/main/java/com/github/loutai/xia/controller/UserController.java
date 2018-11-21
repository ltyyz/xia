package com.github.loutai.xia.controller;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaUser;
import com.github.loutai.xia.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/xia/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/list_all")
    public ServiceResult listAll() {
        return userService.listALl();
    }

    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "rows", defaultValue = "20") int rows,
                                    @RequestParam(name = "username", required = false) String username) {
        Page<XiaUser> pages = userService.queryPage(page, rows, username);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", pages.getContent());
        result.put("total", pages.getTotalElements());
        return result;
    }

    @RequestMapping("/dic")
    public List<Map<String, Object>> dic() {
        return userService.dic();
    }

    @RequestMapping("/add")
    public ServiceResult add(XiaUser entity) {
        return userService.add(entity);
    }

    @RequestMapping("/update")
    public ServiceResult update(XiaUser entity) {
        return userService.update(entity);
    }

    @RequestMapping("/delete")
    public ServiceResult delete(Integer id) {
        return userService.delete(id);
    }
}
