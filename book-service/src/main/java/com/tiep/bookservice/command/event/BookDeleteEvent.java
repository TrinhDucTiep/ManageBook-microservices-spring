package com.tiep.bookservice.command.event;

import lombok.Data;

@Data
public class BookDeleteEvent {
    private String bookId;
}
