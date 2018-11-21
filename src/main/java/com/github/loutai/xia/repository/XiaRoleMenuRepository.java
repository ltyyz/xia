package com.github.loutai.xia.repository;

import com.github.loutai.xia.entity.XiaRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface XiaRoleMenuRepository extends JpaRepository<XiaRoleMenu, Integer> {

    List<XiaRoleMenu> findByRoleId(Integer roleId);

    void deleteByRoleId(Integer roleId);
}
