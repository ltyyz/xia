package com.github.loutai.xia.repository;

import com.github.loutai.xia.entity.XiaUserApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface XiaUserApplicationRepository extends JpaRepository<XiaUserApplication, Integer> {

    @Modifying
    @Query(value = "update xia_user_application set is_default=0 where user_id=?1", nativeQuery = true)
    void removeDefault(Integer userId);
}
