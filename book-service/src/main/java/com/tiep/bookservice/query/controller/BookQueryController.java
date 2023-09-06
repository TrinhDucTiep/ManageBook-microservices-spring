package com.tiep.bookservice.query.controller;

import com.tiep.bookservice.query.model.BookResponseModel;
import com.tiep.bookservice.query.queries.GetAllBooksQuery;
import com.tiep.bookservice.query.queries.GetBookQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books/")
public class BookQueryController {

    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{bookId}")
    public BookResponseModel getBookDetail(@PathVariable String bookId) {
        //Tạo query
        GetBookQuery getBookQuery = new GetBookQuery();
        getBookQuery.setBookId(bookId);

        BookResponseModel bookResponseModel =
                queryGateway.query(getBookQuery, ResponseTypes.instanceOf(BookResponseModel.class))
                            .join(); // lệnh query() này sẽ gọi đến @QueryHandler tương ứng và dùng join() để nhận response

        return bookResponseModel;
    }

    @GetMapping
    public List<BookResponseModel> getAllBook() {
        GetAllBooksQuery getAllBooksQuery = new GetAllBooksQuery();
        List<BookResponseModel> listBook =
                queryGateway.query(getAllBooksQuery, ResponseTypes.multipleInstancesOf(BookResponseModel.class))
                .join();

        return listBook;
    }
}
