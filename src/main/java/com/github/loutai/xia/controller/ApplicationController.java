package com.github.loutai.xia.controller;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaApplication;
import com.github.loutai.xia.service.ApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/xia/application")
public class ApplicationController {

    @Resource
    private ApplicationService applicationService;

    @RequestMapping("/dic")
    public List<Map<String, Object>> dic() {
        return applicationService.dic();
    }

    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "rows", defaultValue = "20") int rows,
                                    @RequestParam(name = "name", required = false) String name) {
        Page<XiaApplication> pages = applicationService.queryPage(page, rows, name);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", pages.getContent());
        result.put("total", pages.getTotalElements());
        return result;
    }

    @RequestMapping("/add")
    public ServiceResult add(XiaApplication entity) {
        return applicationService.add(entity);
    }

    @RequestMapping("/update")
    public ServiceResult update(XiaApplication entity) {
        return applicationService.update(entity);
    }

    @RequestMapping("/delete")
    public ServiceResult delete(Integer id) {
        return applicationService.delete(id);
    }
}
