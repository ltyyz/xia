package com.github.loutai.xia.controller;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaRole;
import com.github.loutai.xia.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/xia/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @RequestMapping("/dic")
    public List<Map<String, Object>> dic() {
        return roleService.dic();
    }

    @RequestMapping("/list_by_app")
    public ServiceResult listByApp(Integer appId) {
        return roleService.listByApp(appId);
    }

    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "rows", defaultValue = "20") int rows,
                                    @RequestParam(name = "appId", required = false) Integer appId,
                                    @RequestParam(name = "name", required = false) String name) {
        Page<XiaRole> pages = roleService.queryPage(page, rows, appId, name);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", pages.getContent());
        result.put("total", pages.getTotalElements());
        return result;
    }

    @RequestMapping("/add")
    public ServiceResult add(XiaRole entity) {
        return roleService.add(entity);
    }

    @RequestMapping("/update")
    public ServiceResult update(XiaRole entity) {
        return roleService.update(entity);
    }

    @RequestMapping("/delete")
    public ServiceResult delete(Integer id) {
        return roleService.delete(id);
    }
}
