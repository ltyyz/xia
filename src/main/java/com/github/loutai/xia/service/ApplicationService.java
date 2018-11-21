package com.github.loutai.xia.service;

import com.github.loutai.xia.common.Constant;
import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaApplication;
import com.github.loutai.xia.repository.XiaApplicationRepository;
import com.github.loutai.xia.security.SecurityContextUtil;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ApplicationService {

    @Resource
    private SecurityContextUtil securityContextUtil;

    @Resource
    private XiaApplicationRepository applicationRepository;

    public Page<XiaApplication> queryPage(int page, int rows, String name) {
        Pageable pageable = PageRequest.of(page - 1, rows, new Sort(Sort.DEFAULT_DIRECTION, "id"));

        XiaApplication entity = new XiaApplication();

        entity.setDeleted(Constant.DELETE_N);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.exact());

        if (!StringUtils.isEmpty(name)) {
            entity.setName(name);
        }

        return applicationRepository.findAll(Example.of(entity, exampleMatcher), pageable);
    }


    public ServiceResult add(XiaApplication entity) {
        XiaApplication _entity = applicationRepository.findByCodeAndDeleted(entity.getCode(), Constant.DELETE_N);
        if (_entity != null) {
            return ServiceResult.ofError("编码已存在！");
        } else {
            entity.setDeleted(Constant.DELETE_N);
            entity.setCreateDate(new Date());
            entity.setCreateBy(securityContextUtil.getCurrentUser().getId());
            applicationRepository.saveAndFlush(entity);
            return ServiceResult.ofSuccess();
        }
    }

    public ServiceResult update(XiaApplication entity) {
        XiaApplication _entity = applicationRepository.getOne(entity.getId());
        _entity.setDescription(entity.getDescription());
        _entity.setName(entity.getName());
        _entity.setUpdateDate(new Date());
        _entity.setType(entity.getType());
        _entity.setUrl(entity.getUrl());
        _entity.setUpdateBy(securityContextUtil.getCurrentUser().getId());
        applicationRepository.saveAndFlush(_entity);
        return ServiceResult.ofSuccess();
    }

    public ServiceResult delete(Integer id) {
        XiaApplication _entity = applicationRepository.getOne(id);
        _entity.setDeleted(Constant.DELETE_Y);
        applicationRepository.saveAndFlush(_entity);
        return ServiceResult.ofSuccess();
    }

    public List<Map<String, Object>> dic() {
        return applicationRepository.dic();
    }
}
