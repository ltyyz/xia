package com.github.loutai.xia.controller;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaDic;
import com.github.loutai.xia.service.DicService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/xia/dic")
public class DicController {

    @Resource
    private DicService dicService;


    @RequestMapping("/dic")
    public Object dic(String code) {
        if (code.indexOf(" ") != -1) {
            throw new RuntimeException("非法值!");
        }
        return dicService.dic(code);
    }

    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "rows", defaultValue = "20") int rows,
                                    @RequestParam(name = "code", required = false) String code) {
        Page<XiaDic> pages = dicService.queryPage(page, rows, code);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", pages.getContent());
        result.put("total", pages.getTotalElements());
        return result;
    }

    @RequestMapping("/add")
    public ServiceResult add(XiaDic entity) {
        return dicService.add(entity);
    }

    @RequestMapping("/update")
    public ServiceResult update(XiaDic entity) {
        return dicService.update(entity);
    }

    @RequestMapping("/delete")
    public ServiceResult delete(Integer id) {
        return dicService.delete(id);
    }
}
