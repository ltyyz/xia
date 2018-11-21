package com.github.loutai.xia.service;

import com.github.loutai.xia.common.Constant;
import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaConfig;
import com.github.loutai.xia.repository.XiaConfigRepository;
import com.github.loutai.xia.security.SecurityContextUtil;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ConfigService {

    @Resource
    private SecurityContextUtil securityContextUtil;

    @Resource
    private XiaConfigRepository configRepository;

    public Page<XiaConfig> queryPage(int page, int rows, Integer appId, String code) {
        Pageable pageable = PageRequest.of(page - 1, rows, new Sort(Sort.DEFAULT_DIRECTION, "id"));

        XiaConfig entity = new XiaConfig();
        entity.setDeleted(Constant.DELETE_N);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("id");
        if (appId != null) {
            entity.setAppId(appId);
        }
        if (!StringUtils.isEmpty(code)) {
            entity.setCode(code);
        }

        return configRepository.findAll(Example.of(entity, exampleMatcher), pageable);
    }


    public ServiceResult add(XiaConfig entity) {
        XiaConfig _entity = configRepository.findByAppIdAndCodeAndDeleted(entity.getAppId(), entity.getCode(), Constant.DELETE_N);
        if (_entity != null) {
            return ServiceResult.ofError("编码已存在！");

        } else {
            entity.setDeleted(Constant.DELETE_N);
            entity.setCreateDate(new Date());
            entity.setCreateBy(securityContextUtil.getCurrentUser().getId());
            configRepository.saveAndFlush(entity);
            return ServiceResult.ofSuccess();
        }
    }

    public ServiceResult update(XiaConfig entity) {
        XiaConfig _entity = configRepository.getOne(entity.getId());
        _entity.setName(entity.getName());
        _entity.setValue(entity.getValue());
        _entity.setDescription(entity.getDescription());
        _entity.setExt1(entity.getExt1());
        _entity.setExt2(entity.getExt2());
        _entity.setExt3(entity.getExt3());
        _entity.setUpdateDate(new Date());
        _entity.setUpdateBy(securityContextUtil.getCurrentUser().getId());
        configRepository.saveAndFlush(_entity);
        return ServiceResult.ofSuccess();
    }

    public ServiceResult delete(Integer id) {
        XiaConfig _entity = configRepository.getOne(id);
        _entity.setDeleted(Constant.DELETE_Y);
        configRepository.saveAndFlush(_entity);
        return ServiceResult.ofSuccess();
    }

    public XiaConfig findByAppIdAndCode(Integer appId, String code) {
        return configRepository.findByAppIdAndCodeAndDeleted(appId, code, Constant.DELETE_N);
    }

}
