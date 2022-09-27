package com.example.homework17.service;

import com.example.homework17.exception.EmployeeAlreadyAddedException;
import com.example.homework17.exception.EmployeeNotFoundException;
import com.example.homework17.exception.EmployeeStorageIsFullException;
import com.example.homework17.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private final int Size = 5;
    private final Map <String,Employee> employees;

    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    public Employee add (String firstName, String lastName) {
       Employee employee = new Employee(firstName, lastName);
       if (employees.containsKey(employee.getFullName()))
           throw new EmployeeAlreadyAddedException();
       if (employees.size() >= Size) {
           throw new EmployeeStorageIsFullException();
       }
       employees.put(employee.getFullName(), employee);
       return employee;
    }
        public Employee remove (String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
            if (employees.containsKey(employee.getFullName())) {
                return employees.remove(employee.getFullName());
            }
        throw new EmployeeNotFoundException();
    }
    public Employee find (String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }
    public Collection <Employee> findAll () {
        return Collections.unmodifiableCollection(employees.values());
    }

}
