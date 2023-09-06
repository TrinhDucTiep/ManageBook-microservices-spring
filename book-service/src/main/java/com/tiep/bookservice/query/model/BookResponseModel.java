package com.tiep.bookservice.query.model;

import lombok.Data;

@Data
public class BookResponseModel {
    private String bookId;
    private String name;
    private String author;
    private boolean isReady;
}
