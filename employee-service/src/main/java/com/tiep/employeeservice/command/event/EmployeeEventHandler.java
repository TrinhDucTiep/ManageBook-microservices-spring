package com.tiep.employeeservice.command.event;

import com.tiep.employeeservice.command.data.Employee;
import com.tiep.employeeservice.command.data.EmployeeRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEventHandler {
    @Autowired
    private EmployeeRepository employeeRepository;

    @EventHandler
    public void on(CreateEmployeeEvent event) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(event, employee);
        employeeRepository.save(employee);
    }

    @EventHandler
    public void on(UpdateEmployeeEvent event) {
        Employee employee = employeeRepository.getById(event.getEmployeeId());
        BeanUtils.copyProperties(event, employee);
        employeeRepository.save(employee);
    }

    @EventHandler
    public void on(DeleteEmployeeEvent event) {
        employeeRepository.deleteById(event.getEmployeeId());
    }
}
