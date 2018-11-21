package com.github.loutai.xia.repository;

import com.github.loutai.xia.entity.XiaDic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XiaDicRepository extends JpaRepository<XiaDic, Integer> {

    List<XiaDic> findByCode(String code);
}
