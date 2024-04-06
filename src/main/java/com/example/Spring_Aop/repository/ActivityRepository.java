package com.example.Spring_Aop.repository;

import com.example.Spring_Aop.entity.*;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends ListCrudRepository<ActivityLog, Long>{
}
