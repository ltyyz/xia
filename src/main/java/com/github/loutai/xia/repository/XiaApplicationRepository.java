package com.github.loutai.xia.repository;

import com.github.loutai.xia.entity.XiaApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface XiaApplicationRepository extends JpaRepository<XiaApplication, Integer> {

    @Query(value = "select t1.* From xia_application t1, xia_user_application t2 where t1.id = t2.app_id and t1.deleted=0 and t2.user_id=?1 order by t2.is_default desc,t1.id", nativeQuery = true)
    List<XiaApplication> getByUserId(Integer userId);

    XiaApplication findByCodeAndDeleted(String code, Integer deleted);

    @Query(value = "select id as value,name as text from xia_application where deleted=0", nativeQuery = true)
    List<Map<String, Object>> dic();

    @Modifying
    @Query(value = "update xia_user_application set is_default=0 where user_id=?1", nativeQuery = true)
    void removeDefault(Integer userId);

    @Modifying
    @Query(value = "update xia_user_application set is_default=1 where user_id=?1 and app_id=?2", nativeQuery = true)
    void setDefault(Integer userId, Integer appId);
}
