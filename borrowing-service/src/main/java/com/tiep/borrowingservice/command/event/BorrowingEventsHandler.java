package com.tiep.borrowingservice.command.event;

import com.tiep.borrowingservice.command.data.Borrowing;
import com.tiep.borrowingservice.command.data.BorrowingRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BorrowingEventsHandler {
    @Autowired
    private BorrowingRepository borrowingRepository;

    @EventHandler
    public void on(BorrowCreatedEvent event) {
        Borrowing borrowing = new Borrowing();
        BeanUtils.copyProperties(event, borrowing);
        borrowingRepository.save(borrowing);
    }
}
