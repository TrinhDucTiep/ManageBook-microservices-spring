package com.tiep.employeeservice.command.model;

import lombok.Data;

@Data
public class EmployeeRequestModel {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;

    public Boolean getIsDisciplined() {
        return isDisciplined;
    }

    public void setIsDisciplined(Boolean disciplined) {
        isDisciplined = disciplined;
    }
}
