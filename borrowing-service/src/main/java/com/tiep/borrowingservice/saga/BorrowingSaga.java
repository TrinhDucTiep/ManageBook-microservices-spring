package com.tiep.borrowingservice.saga;

import com.example.commonservice.command.UpdateStatusBookCommand;
import com.example.commonservice.model.BookResponseCommonModel;
import com.example.commonservice.query.GetDetailsBookQuery;
import com.tiep.borrowingservice.command.command.DeleteBorrowCommand;
import com.tiep.borrowingservice.command.event.BorrowCreatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class BorrowingSaga {
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    private void handle(BorrowCreatedEvent event) {
        System.out.println("BorrowCreatedEvent in Saga for BookId: " + event.getBookId() + " : Employee: " + event.getEmployeeId());

        try {
            SagaLifecycle.associateWith("bookId", event.getBookId());

            GetDetailsBookQuery getDetailsBookQuery = new GetDetailsBookQuery(event.getBookId());
            BookResponseCommonModel bookResponseCommonModel =
                    queryGateway.query(getDetailsBookQuery, ResponseTypes.instanceOf(BookResponseCommonModel.class)).join();
            if (bookResponseCommonModel.getIsReady() == true) {
                UpdateStatusBookCommand updateCommand = new UpdateStatusBookCommand(event.getBookId(), false, event.getEmployeeId(), event.getId());
                commandGateway.sendAndWait(updateCommand);
            } else {
                throw new Exception("Sách đã có người mượn");
            }
        } catch (Exception e) {
            rollBackBorrowRecord(event.getId());
            System.out.println(e.getMessage());
        }

    }

    private void rollBackBorrowRecord(String id) {
        commandGateway.sendAndWait(new DeleteBorrowCommand(id));
    }
}
