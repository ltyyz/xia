package com.github.loutai.xia.repository;

import com.github.loutai.xia.entity.XiaRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XiaRolPermissionRepository extends JpaRepository<XiaRolePermission, Integer> {
}
