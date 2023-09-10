package com.example.commonservice.model;

import lombok.Data;

@Data
public class BookResponseCommonModel {
    private String bookId;
    private String name;
    private String author;
    private boolean isReady;

    public boolean getIsReady() {
        return isReady;
    }

    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
    }
}
