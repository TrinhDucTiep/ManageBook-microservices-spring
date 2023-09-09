package com.tiep.employeeservice.query.projection;

import com.tiep.employeeservice.command.data.Employee;
import com.tiep.employeeservice.command.data.EmployeeRepository;
import com.tiep.employeeservice.query.model.EmployeeResponseModel;
import com.tiep.employeeservice.query.queries.GetAllEmployeeQuery;
import com.tiep.employeeservice.query.queries.GetEmployeeQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeProjection {
    @Autowired
    private EmployeeRepository employeeRepository;

    @QueryHandler
    public EmployeeResponseModel handle(GetEmployeeQuery query) {
        Employee employee = employeeRepository.getById(query.getEmployeeId());
        EmployeeResponseModel model = new EmployeeResponseModel();
        BeanUtils.copyProperties(employee, model);

        return model;
    }

    @QueryHandler
    public List<EmployeeResponseModel> handle(GetAllEmployeeQuery query) {
        List<EmployeeResponseModel> listModel = new ArrayList<>();
        List<Employee> listEmployee = employeeRepository.findAll();
        listEmployee.forEach(employee -> {
            EmployeeResponseModel model = new EmployeeResponseModel();
            BeanUtils.copyProperties(employee, model);
            listModel.add(model);
        });

        return listModel;
    }
}
