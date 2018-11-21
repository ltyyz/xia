package com.github.loutai.xia.service;

import com.github.loutai.xia.common.Constant;
import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaUserApplication;
import com.github.loutai.xia.repository.XiaUserApplicationRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class UserApplicationService {

    @Resource
    private XiaUserApplicationRepository userApplicationRepository;

    public Page<XiaUserApplication> queryPage(int page, int rows, Integer appId, Integer userId) {
        Pageable pageable = PageRequest.of(page - 1, rows, new Sort(Sort.DEFAULT_DIRECTION, "id"));
        XiaUserApplication entity = new XiaUserApplication();
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");

        if (!StringUtils.isEmpty(userId)) {
            entity.setUserId(userId);
        }

        if (appId != null && appId != 0) {
             entity.setAppId(appId);
        }

        return userApplicationRepository.findAll(Example.of(entity, exampleMatcher), pageable);
    }

    @Transactional
    public ServiceResult add(XiaUserApplication entity) {
        userApplicationRepository.saveAndFlush(entity);
        return ServiceResult.ofSuccess();
    }

    @Transactional
    public ServiceResult update(XiaUserApplication entity) {
        XiaUserApplication _entity = userApplicationRepository.getOne(entity.getId());
        _entity.setAppId(entity.getAppId());
        _entity.setUserId(entity.getUserId());
        if (entity.getIsDefault().equals(Constant.IS_DEFAULT_Y)
                && _entity.getIsDefault().equals(Constant.IS_DEFAULT_N)) {
            userApplicationRepository.removeDefault(entity.getUserId());
        }
        _entity.setIsDefault(entity.getIsDefault());
        userApplicationRepository.saveAndFlush(_entity);
        return ServiceResult.ofSuccess();
    }

    @Transactional
    public ServiceResult delete(Integer id) {
        userApplicationRepository.deleteById(id);
        return ServiceResult.ofSuccess();
    }
}
