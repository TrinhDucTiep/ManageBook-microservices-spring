package com.tiep.bookservice.command.aggregate;

import com.tiep.bookservice.command.command.CreateBookCommand;
import com.tiep.bookservice.command.event.BookCreateEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
public class BookAggregate {
    @AggregateIdentifier
    private String bookId;
    private String name;
    private String author;
    private boolean isReady;

    @CommandHandler
    public BookAggregate(CreateBookCommand createBookCommand) {
        // khi có command => phải tạo ra event tương ứng với command đó
        BookCreateEvent bookCreateEvent = new BookCreateEvent();
        BeanUtils.copyProperties(createBookCommand, bookCreateEvent); // copy thuộc tính vào nhau
        AggregateLifecycle.apply(bookCreateEvent); // phát đi cái event này
    }

    @EventSourcingHandler
    // Sau khi nhảy vào đây xong thì nó sẽ tìm đến @EventHandler
    public void on(BookCreateEvent event) {
        this.bookId = event.getBookId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.isReady();
    }
}
