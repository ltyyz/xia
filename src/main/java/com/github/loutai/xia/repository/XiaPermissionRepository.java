package com.github.loutai.xia.repository;

import com.github.loutai.xia.entity.XiaPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XiaPermissionRepository extends JpaRepository<XiaPermission, Integer> {

    List<XiaPermission> findByAppId(Integer appId);

    Long countByParentId(Integer id);

    @Query(value = "select distinct t4.* From xia_user t1, xia_user_role t2,xia_role_permission t3,xia_permission t4 where t1.id = ?1 and t1.id = t2.user_id and t2.role_id=t3.role_id and t3.permission_id = t4.id", nativeQuery = true)
    List<XiaPermission> findUserPermission(Integer userId);
}
