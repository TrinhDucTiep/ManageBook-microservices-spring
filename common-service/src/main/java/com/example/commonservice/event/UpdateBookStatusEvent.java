package com.example.commonservice.event;

import lombok.Data;

@Data
public class UpdateBookStatusEvent {
    private String bookId;
    private Boolean isReady;
    private String employeeId;
    private String borrowId;
}
