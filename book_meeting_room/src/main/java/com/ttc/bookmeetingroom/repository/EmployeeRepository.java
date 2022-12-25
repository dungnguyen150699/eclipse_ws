package com.ttc.bookmeetingroom.repository;

import com.ttc.bookmeetingroom.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
