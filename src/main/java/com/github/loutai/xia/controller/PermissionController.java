package com.github.loutai.xia.controller;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaPermission;
import com.github.loutai.xia.service.PermissionService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/xia/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @RequestMapping("/list_by_app")
    public ServiceResult listByAppId(Integer appId) {
        return permissionService.listByAppId(appId);
    }

    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "rows", defaultValue = "20") int rows,
                                    @RequestParam(name = "appId", required = false) Integer appId,
                                    @RequestParam(name = "parentId", required = false) String parentId) {
        Page<XiaPermission> pages = permissionService.queryPage(page, rows, appId, parentId);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", pages.getContent());
        result.put("total", pages.getTotalElements());
        return result;
    }

    @RequestMapping("/add")
    public ServiceResult add(XiaPermission entity) {
        return permissionService.add(entity);
    }

    @RequestMapping("/update")
    public ServiceResult update(XiaPermission entity) {
        return permissionService.update(entity);
    }

    @RequestMapping("/delete")
    public ServiceResult delete(Integer id) {
        return permissionService.delete(id);
    }
}
