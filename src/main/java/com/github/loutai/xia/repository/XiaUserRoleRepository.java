package com.github.loutai.xia.repository;

import com.github.loutai.xia.entity.XiaUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface XiaUserRoleRepository extends JpaRepository<XiaUserRole, Integer> {

    List<XiaUserRole> findByUserId(Integer userId);

    void deleteByRoleId(Integer roleId);
}
