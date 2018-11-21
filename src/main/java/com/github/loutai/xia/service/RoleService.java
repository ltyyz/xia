package com.github.loutai.xia.service;

import com.github.loutai.xia.common.Constant;
import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaRole;
import com.github.loutai.xia.repository.XiaRoleMenuRepository;
import com.github.loutai.xia.repository.XiaRolePermissionRepository;
import com.github.loutai.xia.repository.XiaRoleRepository;
import com.github.loutai.xia.repository.XiaUserRoleRepository;
import com.github.loutai.xia.security.SecurityContextUtil;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoleService {

    @Resource
    private SecurityContextUtil securityContextUtil;

    @Resource
    private XiaRoleRepository roleRepository;

    @Resource
    private XiaRoleMenuRepository roleMenuRepository;

    @Resource
    private XiaRolePermissionRepository rolePermissionRepository;

    @Resource
    private XiaUserRoleRepository userRoleRepository;

    public Page<XiaRole> queryPage(int page, int rows, Integer appId, String name) {
        Pageable pageable = PageRequest.of(page - 1, rows, new Sort(Sort.DEFAULT_DIRECTION, "id"));

        XiaRole entity = new XiaRole();

        entity.setDeleted(Constant.DELETE_N);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id")
                .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.exact());

        if (!StringUtils.isEmpty(name)) {
            entity.setName(name);
            exampleMatcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        }

        if (appId != null && appId != 0) {
             entity.setAppId(appId);
            exampleMatcher.withMatcher("appId", ExampleMatcher.GenericPropertyMatchers.exact());
        }

        Example<XiaRole> example = Example.of(entity, exampleMatcher);
        return roleRepository.findAll(example, pageable);
    }


    public ServiceResult add(XiaRole entity) {
        XiaRole _entity = roleRepository.findByAppIdAndCodeAndDeleted(entity.getAppId(), entity.getCode(), Constant.DELETE_N);
        if (_entity != null) {
            return ServiceResult.ofError("编码已存在");
        }
        entity.setDeleted(Constant.DELETE_N);
        entity.setCreateDate(new Date());
        entity.setCreateBy(securityContextUtil.getCurrentUser().getId());
        roleRepository.saveAndFlush(entity);
        return ServiceResult.ofSuccess();
    }

    public ServiceResult update(XiaRole entity) {
        XiaRole _entity = roleRepository.findByAppIdAndCodeAndDeletedAndIdNot(entity.getAppId(), entity.getCode(), Constant.DELETE_N, entity.getId());
        if (_entity != null) {
            return ServiceResult.ofError("编码已存在");
        }
        _entity = roleRepository.getOne(entity.getId());
        _entity.setName(entity.getName());
        _entity.setAppId(entity.getAppId());
        _entity.setUpdateDate(new Date());
        _entity.setCode(entity.getCode());
        _entity.setUpdateBy(securityContextUtil.getCurrentUser().getId());
        roleRepository.saveAndFlush(_entity);
        return ServiceResult.ofSuccess();
    }

    @Transactional
    public ServiceResult delete(Integer id) {
        XiaRole _entity = roleRepository.getOne(id);
        _entity.setDeleted(Constant.DELETE_Y);
        roleRepository.saveAndFlush(_entity);
        roleMenuRepository.deleteByRoleId(id);
        rolePermissionRepository.deleteByRoleId(id);
        userRoleRepository.deleteByRoleId(id);
        return ServiceResult.ofSuccess();
    }

    public ServiceResult listByApp(Integer appId) {
        return ServiceResult.ofSuccess(roleRepository.findByAppIdAndDeleted(appId, Constant.DELETE_N));
    }

    public List<Map<String, Object>> dic() {
        return roleRepository.dic();
    }
}
