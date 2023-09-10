package com.example.commonservice.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class UpdateStatusBookCommand {
    @TargetAggregateIdentifier
    private String bookId;
    private boolean isReady;
    private String employeeId;
    private String borrowId;
}
