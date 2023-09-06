package com.tiep.bookservice.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/*
Tách biệt trách nhiệm: BookRequestModel chỉ có trách nhiệm đại diện cho dữ liệu đầu vào từ client,
 trong khi CreateBookCommand có trách nhiệm xác định cách xử lý dữ liệu đó để tạo một cuốn sách.
 */
@Data
@AllArgsConstructor
public class CreateBookCommand {
    @TargetAggregateIdentifier
    private String bookId;
    private String name;
    private String author;
    private boolean isReady;
}
