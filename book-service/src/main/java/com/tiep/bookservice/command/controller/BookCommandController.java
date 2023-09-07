package com.tiep.bookservice.command.controller;

import com.tiep.bookservice.command.command.CreateBookCommand;
import com.tiep.bookservice.command.command.DeleteBookCommand;
import com.tiep.bookservice.command.command.UpdateBookCommand;
import com.tiep.bookservice.command.model.BookRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookCommandController {

//    @Autowired
    private final CommandGateway commandGateway;

    @Autowired
    public BookCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addBook(@RequestBody BookRequestModel model) {
        CreateBookCommand command = new CreateBookCommand(UUID.randomUUID().toString(), model.getName(), model.getAuthor(), true);
        commandGateway.sendAndWait(command);
        // khi gửi đi 1 command thì command bus sẽ gửi cái command của mình đi
        // => cần phải có 1 thằng commandHandler để xử lý cái command này
        // những domain model được gọi là aggregate và commandHandler sẽ dựa vào chúng để xử lý
        return "added book";
    }

    @PutMapping
    public String updateBook(@RequestBody BookRequestModel model) {
        UpdateBookCommand command = new UpdateBookCommand(model.getBookId(), model.getName(), model.getAuthor(), model.getIsReady());
        commandGateway.sendAndWait(command);

        return "updated book";
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId) {
        DeleteBookCommand command = new DeleteBookCommand(bookId);
        commandGateway.sendAndWait(command);

        return "deleted book";
    }
}
