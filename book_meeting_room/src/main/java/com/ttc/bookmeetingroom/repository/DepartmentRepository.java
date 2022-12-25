package com.ttc.bookmeetingroom.repository;

import com.ttc.bookmeetingroom.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DepartmentRepository extends JpaRepository<Department, String> {

    @Query(value = "select * from department d where "
            + "(:id is null  or d.id like  %:id%) "
            + "and (:name is null or d.name like %:name%)", nativeQuery = true )
    Page<Department> getAllByCondition(@Param("id") Integer id, @Param("name") String name, Pageable pageable);

    Department findById(Integer id);

    @Modifying
    void deleteById(Integer id);

}
