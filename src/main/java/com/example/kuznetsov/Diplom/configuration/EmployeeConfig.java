package com.example.kuznetsov.Diplom.configuration;

import com.example.kuznetsov.Diplom.employees.Employee;
import com.example.kuznetsov.Diplom.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            EmployeeRepository employeeRepository
    ) {
        return (args) -> {
            var employeeList = List.of(new Employee(
                            null,
                            "Vasiliy",
                            "vasekku25@gmail.com",
                            LocalDate.of(1976, 11, 19),
                            90000,
                            48
                    ),
                    new Employee(
                            null,
                            "Pavel",
                            "pavel@gmail.com",
                            LocalDate.of(1975, 4, 6),
                            80000,
                            49
                    )

            );
            employeeRepository.saveAll(employeeList);

        };

    }
}
