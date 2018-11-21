package com.github.loutai.xia.service;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaUserRole;
import com.github.loutai.xia.repository.XiaUserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleService {

    @Resource
    private XiaUserRoleRepository userRoleRepository;

    public ServiceResult listByUserId(Integer userId) {
        return ServiceResult.ofSuccess(userRoleRepository.findByUserId(userId));
    }

    public ServiceResult save(Integer userId, String[] roleIds) {
        List<XiaUserRole> newUserRoles = Arrays.stream(roleIds)
                .filter(roleId -> !StringUtils.isEmpty(roleId))
                .map(roleId -> XiaUserRole.of(userId, Integer.parseInt(roleId)))
                .collect(Collectors.toList());
        List<XiaUserRole> currentUserRoles = userRoleRepository.findByUserId(userId);

        List<XiaUserRole> retains = new ArrayList<>();
        currentUserRoles.forEach(currentUserRole -> {
            boolean exists = false;
            for (XiaUserRole newUserRole : newUserRoles) {
                if (newUserRole.getRoleId().equals(currentUserRole.getRoleId())
                        && newUserRole.getRoleId().equals(currentUserRole.getRoleId())) {
                    retains.add(currentUserRole);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                userRoleRepository.deleteById(currentUserRole.getId());
            }
        });

        newUserRoles.forEach(newUserRole -> {
            boolean exists = false;
            for (XiaUserRole retain : retains) {
                if (newUserRole.getRoleId().equals(retain.getRoleId())
                        && newUserRole.getRoleId().equals(retain.getRoleId())) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                userRoleRepository.saveAndFlush(newUserRole);
            }
        });

        return ServiceResult.ofSuccess();
    }
}
