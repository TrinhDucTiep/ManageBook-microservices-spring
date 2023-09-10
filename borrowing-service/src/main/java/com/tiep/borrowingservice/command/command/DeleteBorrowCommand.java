package com.tiep.borrowingservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class DeleteBorrowCommand {
    @TargetAggregateIdentifier
    private String id;
}
