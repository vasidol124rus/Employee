package com.example.kuznetsov.Diplom.services;

import com.example.kuznetsov.Diplom.employees.Employee;
import com.example.kuznetsov.Diplom.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();

    }

    public Employee createEmployee(Employee employee) {

        if (employee.getId() != null) {
            throw new IllegalArgumentException("id must be empty");
        }

        if (employeeRepository.findByEmail(employee.getEmail())
                .isPresent()) {
            throw new IllegalArgumentException("Email already takes");
        }

        if (employee.getSalary() <= 5000) {
            throw new IllegalArgumentException("Salary must be bigger than 5000");

        }

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        if (employeeRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("Employee not found by id=%s".formatted(id));
        }
        employeeRepository.deleteById(id);
    }
    @Transactional
    public void updateEmployee(Long id, String email, Integer salary) {
        {
            var employee =employeeRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found by id=%s"
                            .formatted(id))
                    );
            if (email != null
                && !email.isEmpty()
                && !email.equals(employee.getEmail()))
            {
                Optional<Employee> employeeOpt = employeeRepository.findByEmail(email);
                if (employeeOpt.isPresent()){
                    throw new IllegalArgumentException("Email already taken");

                }

                employee.setEmail(email);
            }
            if (salary != null){
                if (salary <= 5000){
                    throw new IllegalArgumentException("Salary must be bigger than 5000");

                }
                employee.setSalary(salary);
            }

      }
    }
}
