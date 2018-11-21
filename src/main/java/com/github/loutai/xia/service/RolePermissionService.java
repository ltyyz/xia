package com.github.loutai.xia.service;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaRolePermission;
import com.github.loutai.xia.repository.XiaRolePermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolePermissionService {

    @Resource
    private XiaRolePermissionRepository rolePermissionRepository;

    public ServiceResult listByRoleId(Integer roleId) {
        return ServiceResult.ofSuccess(rolePermissionRepository.findByRoleId(roleId));
    }

    public ServiceResult save(Integer roleId, String[] permissionIds) {
        List<XiaRolePermission> newRolePermissions = Arrays.stream(permissionIds)
                .filter(permissionId -> !StringUtils.isEmpty(permissionId))
                .map(permissionId -> XiaRolePermission.of(roleId, Integer.parseInt(permissionId)))
                .collect(Collectors.toList());
        List<XiaRolePermission> currentRolePermissions = rolePermissionRepository.findByRoleId(roleId);

        List<XiaRolePermission> retains = new ArrayList<>();
        currentRolePermissions.forEach(currentRolePermission -> {
            boolean exists = false;
            for (XiaRolePermission newRolePermission : newRolePermissions) {
                if (newRolePermission.getRoleId().equals(currentRolePermission.getRoleId())
                        && newRolePermission.getPermissionId().equals(currentRolePermission.getPermissionId())) {
                    retains.add(currentRolePermission);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                rolePermissionRepository.deleteById(currentRolePermission.getId());
            }
        });

        newRolePermissions.forEach(newRolePermission -> {
            boolean exists = false;
            for (XiaRolePermission retain : retains) {
                if (newRolePermission.getRoleId().equals(retain.getRoleId())
                        && newRolePermission.getPermissionId().equals(retain.getPermissionId())) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                rolePermissionRepository.saveAndFlush(newRolePermission);
            }
        });

        return ServiceResult.ofSuccess();
    }
}
