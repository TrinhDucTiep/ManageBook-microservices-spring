package com.tiep.bookservice.command.event;

import lombok.Data;

@Data
public class BookCreateEvent {
    private String bookId;
    private String name;
    private String author;
    private boolean isReady;
}
