package com.github.loutai.xia.repository;

import com.github.loutai.xia.entity.XiaRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XiaRolePermissionRepository extends JpaRepository<XiaRolePermission, Integer> {

    List<XiaRolePermission> findByRoleId(Integer roleId);

    void deleteByRoleId(Integer roleId);
}
