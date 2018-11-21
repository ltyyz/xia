package com.github.loutai.xia.repository;

import com.github.loutai.xia.entity.XiaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface XiaUserRepository extends JpaRepository<XiaUser, Integer> {

    XiaUser findByAccountAndDeleted(String account, Integer deleted);

    XiaUser findByAccountAndDeletedAndStatus(String account, Integer deleted, String status);

    @Query(value = "select id as value,username as text from xia_user where deleted=0", nativeQuery = true)
    List<Map<String, Object>> dic();

    List<XiaUser> findByDeleted(Integer deleted);
}
