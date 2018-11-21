package com.github.loutai.xia.repository;

import com.github.loutai.xia.entity.XiaRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface XiaRoleRepository extends JpaRepository<XiaRole, Integer> {
    XiaRole findByAppIdAndCodeAndDeleted(Integer appId, String code, Integer deleted);

    XiaRole findByAppIdAndCodeAndDeletedAndIdNot(Integer appId, String code, Integer deleted, Integer id);

    List<XiaRole> findByAppIdAndDeleted(Integer appId, Integer deleted);

    @Query(value = "select t1.id as value, t1.name as text, t2.name as appName, t1.app_id as appId from xia_role t1, xia_application t2 where t1.app_id = t2.id and t1.deleted=0", nativeQuery = true)
    List<Map<String, Object>> dic();
}
