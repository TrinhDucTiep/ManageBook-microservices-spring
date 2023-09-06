package com.tiep.bookservice.command.data;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name="books")
public class Book {
    @Id
    private String bookId;
    private String name;
    private String author;
    private boolean isReady;
}
