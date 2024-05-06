package com.example.demo.repository;

import com.example.demo.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    @Query(value = "from UserInfo e where (:id is null or e.id = :id) or (:name is null or e.name = :name) and (:deptId is null or e.dept.id = :deptId) and (:positionId is null or e.position.id = :positionId)")
    Page<UserInfo> findUser(@Param("id") String id, @Param("name") String name, @Param("deptId") String deptId, @Param("positionId") Integer positionId, Pageable pageable);


}
