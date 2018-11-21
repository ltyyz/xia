package com.github.loutai.xia.repository;

import com.github.loutai.xia.entity.XiaMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XiaMenuRepository extends JpaRepository<XiaMenu, Integer> {

    @Query(value = "select distinct t3.* from xia_user_role t1, xia_role_menu t2, xia_menu t3 " +
            "where t1.user_id=?1 " +
            "and t1.role_id = t2.role_id " +
            "and t2.menu_id = t3.id " +
            "and t3.app_id = ?2 " +
            "and t3.deleted=0 " +
            "order by t3.sorts", nativeQuery = true)
    List<XiaMenu> getUserMenuByAppId(Integer id, Integer appId);

    List<XiaMenu> findByAppIdAndDeletedOrderBySorts(Integer appId, Integer deleted);

    Long countByParentIdAndDeleted(Integer parentId, Integer deleted);
}
