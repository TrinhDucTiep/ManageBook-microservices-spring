package com.tiep.bookservice.command.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Book")
public class Book {
    @Id
    private String bookId;
    private String name;
    private String author;
    private boolean isReady;
}
