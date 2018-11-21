package com.github.loutai.xia.service;

import com.github.loutai.xia.repository.XiaDicRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommonService {

    @Resource
    private XiaDicRepository dicRepository;

}
