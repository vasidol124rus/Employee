package com.example.kuznetsov.Diplom.repositories;

import com.example.kuznetsov.Diplom.employees.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

}
