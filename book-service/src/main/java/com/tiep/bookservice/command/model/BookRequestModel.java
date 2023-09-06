package com.tiep.bookservice.command.model;

import lombok.Data;

@Data
public class BookRequestModel {
    private String bookId;
    private String name;
    private String author;
    private boolean isReady;

    public boolean getIsReady() {
        return isReady;
    }

    public void setIsReady(boolean ready) {
        isReady = ready;
    }
}
