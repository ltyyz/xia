package com.github.loutai.xia.service;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaPermission;
import com.github.loutai.xia.repository.XiaPermissionRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class PermissionService {

    @Resource
    private XiaPermissionRepository permissionRepository;

    public ServiceResult listByAppId(Integer appId) {
        return ServiceResult.ofSuccess(permissionRepository.findByAppId(appId));
    }

    public Page<XiaPermission> queryPage(int page, int rows, Integer appId, String parentId) {
        Pageable pageable = PageRequest.of(page - 1, rows, new Sort(Sort.DEFAULT_DIRECTION, "id"));

        XiaPermission entity = new XiaPermission();

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");

        if (!StringUtils.isEmpty(parentId)) {
            entity.setParentId(Integer.parseInt(parentId));
            exampleMatcher.withMatcher("parentId", ExampleMatcher.GenericPropertyMatchers.exact());
        }

        if (appId != null && appId != 0) {
            entity.setAppId(appId);
            exampleMatcher.withMatcher("appId", ExampleMatcher.GenericPropertyMatchers.exact());
        }

        Example<XiaPermission> example = Example.of(entity, exampleMatcher);
        return permissionRepository.findAll(example, pageable);
    }


    public ServiceResult add(XiaPermission entity) {
        entity.setChildren(0);
        permissionRepository.saveAndFlush(entity);

        if (entity.getParentId() != null) {
            updateChildren(entity.getParentId());
        }
        return ServiceResult.ofSuccess();
    }

    public ServiceResult update(XiaPermission entity) {
        XiaPermission _entity = permissionRepository.getOne(entity.getId());
        Integer oldParentId = _entity.getParentId();
        Integer newParentId = entity.getParentId();
        _entity.setName(entity.getName());
        _entity.setAppId(entity.getAppId());
        _entity.setType(entity.getType());
        _entity.setResource(entity.getResource());
        _entity.setParentId(newParentId);
        permissionRepository.saveAndFlush(_entity);

        if (newParentId != oldParentId) {
            updateChildren(newParentId);
            updateChildren(oldParentId);
        }
        return ServiceResult.ofSuccess();
    }

    public ServiceResult delete(Integer id) {
        XiaPermission permission = permissionRepository.findById(id).get();
        if (permission.getChildren() != 0) {
            return ServiceResult.ofError("有子项无法删除！");
        }
        permissionRepository.deleteById(id);
        updateChildren(permission.getParentId());
        return ServiceResult.ofSuccess();
    }

    private void updateChildren(Integer id) {
        if (id != null) {
            Long children = permissionRepository.countByParentId(id);
            XiaPermission permission = permissionRepository.getOne(id);
            permission.setChildren(children.intValue());
            permissionRepository.saveAndFlush(permission);
        }
    }
}
