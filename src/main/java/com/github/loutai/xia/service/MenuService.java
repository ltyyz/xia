package com.github.loutai.xia.service;

import com.github.loutai.xia.common.Constant;
import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaMenu;
import com.github.loutai.xia.repository.XiaMenuRepository;
import com.github.loutai.xia.security.SecurityContextUtil;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MenuService {

    @Resource
    private XiaMenuRepository menuRepository;

    @Resource
    private SecurityContextUtil securityContextUtil;

    public ServiceResult listByAppId(Integer appId) {
        return ServiceResult.ofSuccess(menuRepository.findByAppIdAndDeletedOrderBySorts(appId, Constant.DELETE_N));
    }

    public Page<XiaMenu> queryPage(int page, int rows, Integer appId, String parentId) {
        Pageable pageable = PageRequest.of(page - 1, rows, new Sort(Sort.DEFAULT_DIRECTION, "sorts"));

        XiaMenu entity = new XiaMenu();

        entity.setDeleted(Constant.DELETE_N);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id")
                .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.exact());

        if (!StringUtils.isEmpty(parentId)) {
            entity.setParentId(Integer.parseInt(parentId));
            exampleMatcher.withMatcher("parentId", ExampleMatcher.GenericPropertyMatchers.exact());
        }

        if (appId != null && appId != 0) {
            entity.setAppId(appId);
            exampleMatcher.withMatcher("appId", ExampleMatcher.GenericPropertyMatchers.exact());
        }

        Example<XiaMenu> example = Example.of(entity, exampleMatcher);
        return menuRepository.findAll(example, pageable);
    }


    public ServiceResult add(XiaMenu entity) {
        entity.setDeleted(Constant.DELETE_N);
        entity.setCreateDate(new Date());
        entity.setCreateBy(securityContextUtil.getCurrentUser().getId());
        entity.setChildren(0);
        menuRepository.saveAndFlush(entity);

        if (entity.getParentId() != null) {
            updateChildren(entity.getParentId());
        }
        return ServiceResult.ofSuccess();
    }

    public ServiceResult update(XiaMenu entity) {
        XiaMenu _entity = menuRepository.getOne(entity.getId());
        Integer oldParentId = _entity.getParentId();
        Integer newParentId = entity.getParentId();
        _entity.setName(entity.getName());
        _entity.setAppId(entity.getAppId());
        _entity.setUpdateDate(new Date());
        _entity.setType(entity.getType());
        _entity.setUrl(entity.getUrl());
        _entity.setIcon(entity.getIcon());
        _entity.setSorts(entity.getSorts());
        _entity.setUpdateBy(securityContextUtil.getCurrentUser().getId());
        _entity.setParentId(newParentId);
        menuRepository.saveAndFlush(_entity);

        if (newParentId != oldParentId) {
            updateChildren(newParentId);
            updateChildren(oldParentId);
        }
        return ServiceResult.ofSuccess();
    }

    public ServiceResult delete(Integer id) {
        XiaMenu _entity = menuRepository.getOne(id);
        _entity.setDeleted(Constant.DELETE_Y);
        menuRepository.saveAndFlush(_entity);
        updateChildren(_entity.getParentId());
        return ServiceResult.ofSuccess();
    }

    private void updateChildren(Integer id) {
        if (id != null) {
            Long children = menuRepository.countByParentIdAndDeleted(id, Constant.DELETE_N);
            XiaMenu menu = menuRepository.getOne(id);
            menu.setChildren(children.intValue());
            menuRepository.saveAndFlush(menu);
        }
    }
}
