package com.tiep.bookservice.command.event;

import com.example.commonservice.event.UpdateBookStatusEvent;
import com.tiep.bookservice.command.data.Book;
import com.tiep.bookservice.command.data.BookRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookEventsHandler {

    @Autowired
    private BookRepository bookRepository;

    @EventHandler
    public void on(BookCreateEvent event) {
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookUpdateEvent event) {
        Book book = bookRepository.getById(event.getBookId());
        book.setAuthor(event.getAuthor());
        book.setName(event.getName());
        book.setReady(event.isReady());
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookDeleteEvent event) {
        bookRepository.deleteById(event.getBookId());
    }

    @EventHandler
    public void on(UpdateBookStatusEvent event) {
        Book book = bookRepository.getById(event.getBookId());
        book.setReady(event.getIsReady());
    }
}
