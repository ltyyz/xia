package com.github.loutai.xia.service;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaDic;
import com.github.loutai.xia.repository.XiaDicRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DicService {

    @Resource
    private XiaDicRepository dicRepository;

    public Page<XiaDic> queryPage(int page, int rows, String code) {
        Pageable pageable = PageRequest.of(page - 1, rows, new Sort(Sort.DEFAULT_DIRECTION, "id"));

        XiaDic entity = new XiaDic();
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");
        if (!StringUtils.isEmpty(code)) {
            entity.setCode(code);
            exampleMatcher.withMatcher("code", ExampleMatcher.GenericPropertyMatchers.exact());
        }
        Example<XiaDic> example = Example.of(entity, exampleMatcher);
        return dicRepository.findAll(example, pageable);
    }

    public ServiceResult add(XiaDic entity) {
        dicRepository.saveAndFlush(entity);
        return ServiceResult.ofSuccess();
    }

    public ServiceResult update(XiaDic entity) {
        XiaDic _entity = dicRepository.getOne(entity.getId());
        _entity.setValue(entity.getValue());
        _entity.setName(entity.getName());
        _entity.setDescription(entity.getDescription());
        _entity.setExt1(entity.getExt1());
        _entity.setExt2(entity.getExt2());
        _entity.setExt3(entity.getExt3());
        dicRepository.saveAndFlush(_entity);
        return ServiceResult.ofSuccess();
    }

    public ServiceResult delete(Integer id) {
        XiaDic dic = dicRepository.findById(id).get();
        if (dic.getCode().equals("dic") && dic.getValue().equals("dic")) {
            throw new RuntimeException("内部错误！");
        }
        dicRepository.deleteById(id);
        return ServiceResult.ofSuccess();
    }

    public List<XiaDic> dic(String code) {
        return dicRepository.findByCode(code);
    }
}
