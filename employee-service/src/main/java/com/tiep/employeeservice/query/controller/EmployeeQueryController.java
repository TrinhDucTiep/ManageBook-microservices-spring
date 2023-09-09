package com.tiep.employeeservice.query.controller;

import com.tiep.employeeservice.query.model.EmployeeResponseModel;
import com.tiep.employeeservice.query.queries.GetAllEmployeeQuery;
import com.tiep.employeeservice.query.queries.GetEmployeeQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{employeeId}")
    public EmployeeResponseModel getEmployeeDetail(@PathVariable String employeeId) {
        GetEmployeeQuery query = new GetEmployeeQuery();
        query.setEmployeeId(employeeId);
        EmployeeResponseModel model =
                queryGateway.query(query, ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
        return model;
    }

    @GetMapping
    public List<EmployeeResponseModel> getAllEmployee() {
        GetAllEmployeeQuery query = new GetAllEmployeeQuery();
        List<EmployeeResponseModel> listEmployee =
                queryGateway.query(query, ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
        return listEmployee;
    }
}
