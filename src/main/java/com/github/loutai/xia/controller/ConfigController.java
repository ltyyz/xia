package com.github.loutai.xia.controller;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaConfig;
import com.github.loutai.xia.service.ConfigService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/xia/config")
public class ConfigController {

    @Resource
    private ConfigService configService;

    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "rows", defaultValue = "20") int rows,
                                    @RequestParam(name = "appId", required = false) Integer appId,
                                    @RequestParam(name = "code", required = false) String code) {
        Page<XiaConfig> pages = configService.queryPage(page, rows, appId, code);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", pages.getContent());
        result.put("total", pages.getTotalElements());
        return result;
    }

    @RequestMapping("/add")
    public ServiceResult add(XiaConfig entity) {
        return configService.add(entity);
    }

    @RequestMapping("/update")
    public ServiceResult update(XiaConfig entity) {
        return configService.update(entity);
    }

    @RequestMapping("/delete")
    public ServiceResult delete(Integer id) {
        return configService.delete(id);
    }
}
