package com.tiep.employeeservice.command.event;

import lombok.Data;

@Data
public class DeleteEmployeeEvent {
    private String employeeId;
}
