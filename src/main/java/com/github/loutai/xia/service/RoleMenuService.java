package com.github.loutai.xia.service;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaRoleMenu;
import com.github.loutai.xia.repository.XiaRoleMenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleMenuService {

    @Resource
    private XiaRoleMenuRepository roleMenuRepository;

    public ServiceResult listByRoleId(Integer roleId) {
        return ServiceResult.ofSuccess(roleMenuRepository.findByRoleId(roleId));
    }

    public ServiceResult save(Integer roleId, String[] menuIds) {
        List<XiaRoleMenu> newRoleMenus = Arrays.stream(menuIds)
                .filter(menuId -> !StringUtils.isEmpty(menuId))
                .map(menuId -> XiaRoleMenu.of(roleId, Integer.parseInt(menuId)))
                .collect(Collectors.toList());
        List<XiaRoleMenu> currentRoleMenus = roleMenuRepository.findByRoleId(roleId);

        List<XiaRoleMenu> retains = new ArrayList<>();
        currentRoleMenus.forEach(currentRoleMenu -> {
            boolean exists = false;
            for (XiaRoleMenu newRoleMenu : newRoleMenus) {
                if (newRoleMenu.getRoleId().equals(currentRoleMenu.getRoleId())
                        && newRoleMenu.getMenuId().equals(currentRoleMenu.getMenuId())) {
                    retains.add(currentRoleMenu);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                roleMenuRepository.deleteById(currentRoleMenu.getId());
            }
        });

        newRoleMenus.forEach(newRoleMenu -> {
            boolean exists = false;
            for (XiaRoleMenu retain : retains) {
                if (newRoleMenu.getRoleId().equals(retain.getRoleId())
                        && newRoleMenu.getMenuId().equals(retain.getMenuId())) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                roleMenuRepository.saveAndFlush(newRoleMenu);
            }
        });

        return ServiceResult.ofSuccess();
    }
}
