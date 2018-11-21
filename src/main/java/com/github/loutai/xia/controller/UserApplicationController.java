package com.github.loutai.xia.controller;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaUserApplication;
import com.github.loutai.xia.service.UserApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/xia/userApplication")
public class UserApplicationController {
    
    @Resource
    private UserApplicationService userApplicationService;

    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "rows", defaultValue = "20") int rows,
                                    @RequestParam(name = "appId", required = false) Integer appId,
                                    @RequestParam(name = "userId", required = false) Integer userId) {
        Page<XiaUserApplication> pages = userApplicationService.queryPage(page, rows, appId, userId);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", pages.getContent());
        result.put("total", pages.getTotalElements());
        return result;
    }

    @RequestMapping("/add")
    public ServiceResult add(XiaUserApplication entity) {
        return userApplicationService.add(entity);
    }

    @RequestMapping("/update")
    public ServiceResult update(XiaUserApplication entity) {
        return userApplicationService.update(entity);
    }

    @RequestMapping("/delete")
    public ServiceResult delete(Integer id) {
        return userApplicationService.delete(id);
    }
}
