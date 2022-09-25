package com.example.homework17.service;

import com.example.homework17.exception.EmployeeAlreadyAddedException;
import com.example.homework17.exception.EmployeeNotFoundException;
import com.example.homework17.exception.EmployeeStorageIsFullException;
import com.example.homework17.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final int Size = 5;
    private final List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>();
    }

    public Employee add (String firstName, String lastName) {
       Employee employee = new Employee(firstName, lastName);
       if (employees.contains(employee))
           throw new EmployeeAlreadyAddedException();
       if (employees.size() >= Size) {
           throw new EmployeeStorageIsFullException();
       }
       employees.add(employee);
       return employee;
    }
        public Employee remove (String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
            if (employees.contains(employee)) {
             employees.remove(employee);
             return employee;
            }
        throw new EmployeeNotFoundException();
    }
    public Employee find (String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }
    public List<Employee> findAll () {
        return new ArrayList<>(employees);
    }

}
