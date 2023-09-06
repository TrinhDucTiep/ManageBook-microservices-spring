package com.tiep.bookservice.command.event;

import lombok.Data;

@Data
public class BookUpdateEvent {
    private String bookId; // AxonFramework sẽ tự động tạo ra eventIdentifier sau khi có command tương ứng nên ta sẽ không cần quan tâm
    private String name;
    private String author;
    private boolean isReady;
}
