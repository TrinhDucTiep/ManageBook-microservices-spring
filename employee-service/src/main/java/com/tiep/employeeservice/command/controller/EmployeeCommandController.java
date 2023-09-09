package com.tiep.employeeservice.command.controller;


import com.tiep.employeeservice.command.command.CreateEmployeeCommand;
import com.tiep.employeeservice.command.command.DeleteEmployeeCommand;
import com.tiep.employeeservice.command.command.UpdateEmployeeCommand;
import com.tiep.employeeservice.command.model.EmployeeRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Source;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
@EnableKafka
public class EmployeeCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public String addEmployee(@RequestBody EmployeeRequestModel model) {
        CreateEmployeeCommand command =
                new CreateEmployeeCommand(UUID.randomUUID().toString(), model.getFirstName(), model.getLastName(), model.getKin(), false);
        commandGateway.sendAndWait(command);
        return "added employee";
    }

    @PutMapping
    public String updateEmployee(@RequestBody EmployeeRequestModel model) {
        UpdateEmployeeCommand command =
                new UpdateEmployeeCommand(model.getEmployeeId(), model.getFirstName(), model.getLastName(), model.getKin(), model.getIsDisciplined());
        commandGateway.sendAndWait(command);
        return "updated employee";
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId) {
        DeleteEmployeeCommand command = new DeleteEmployeeCommand(employeeId);
        commandGateway.sendAndWait(command);
        return "deleted employee";
    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody String message) {
            kafkaTemplate.send("trinhtiep", message);
    }
}
