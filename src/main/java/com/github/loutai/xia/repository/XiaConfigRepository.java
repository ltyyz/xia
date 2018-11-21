package com.github.loutai.xia.repository;

import com.github.loutai.xia.entity.XiaConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XiaConfigRepository extends JpaRepository<XiaConfig, Integer> {

    XiaConfig findByCodeAndDeleted(String code, Integer deleted);

    XiaConfig findByAppIdAndCodeAndDeleted(Integer appId, String code, Integer deleted);
}
