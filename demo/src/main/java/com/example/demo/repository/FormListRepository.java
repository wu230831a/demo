package com.example.demo.repository;

import com.example.demo.entity.FormList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FormListRepository extends JpaRepository<FormList, String> {
    
}
