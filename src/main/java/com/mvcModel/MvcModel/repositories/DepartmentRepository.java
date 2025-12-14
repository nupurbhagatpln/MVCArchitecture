package com.mvcModel.MvcModel.repositories;

import com.mvcModel.MvcModel.dtos.DepartmentDto;
import com.mvcModel.MvcModel.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
