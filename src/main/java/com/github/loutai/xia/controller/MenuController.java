package com.github.loutai.xia.controller;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaMenu;
import com.github.loutai.xia.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/xia/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @RequestMapping("/list_by_app")
    public ServiceResult listByAppId(Integer appId) {
        return menuService.listByAppId(appId);
    }

    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "rows", defaultValue = "20") int rows,
                                    @RequestParam(name = "appId", required = false) Integer appId,
                                    @RequestParam(name = "parentId", required = false) String parentId) {
        Page<XiaMenu> pages = menuService.queryPage(page, rows, appId, parentId);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", pages.getContent());
        result.put("total", pages.getTotalElements());
        return result;
    }

    @RequestMapping("/add")
    public ServiceResult add(XiaMenu entity) {
        return menuService.add(entity);
    }

    @RequestMapping("/update")
    public ServiceResult update(XiaMenu entity) {
        return menuService.update(entity);
    }

    @RequestMapping("/delete")
    public ServiceResult delete(Integer id) {
        return menuService.delete(id);
    }
}
