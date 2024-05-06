package com.example.demo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.FormInfo;

import java.util.Date;
import java.util.List;

public interface FormInfoRepository extends JpaRepository<FormInfo, Integer> {
    @Query("from FormInfo where deadline < :date")
    List<FormInfo> checkFormTime(@Param("date") Date date);

    @Query("from FormInfo where applyUserId = :userId and statusId != 1")
    Page<FormInfo> getUserCompletedForm(@Param("userId") String userId, Pageable pageable);
    
    @Query("from FormInfo where applyUserId = :userId and statusId = 1")
    Page<FormInfo> getUserPendingForm(@Param("userId") String userId, Pageable pageable);

    @Query("from FormInfo where reviewerId = :adminId and statusId != 1")
    Page<FormInfo> getAdminCompletedForm(@Param("adminId") String adminId, Pageable pageable);
    
    @Query("from FormInfo where reviewerId = :adminId and statusId = 1")
    Page<FormInfo> getAdminPendingForm(@Param("adminId") String adminId, Pageable pageable);
}
