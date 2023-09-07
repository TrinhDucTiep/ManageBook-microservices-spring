package com.tiep.bookservice.query.projection;

import com.tiep.bookservice.command.data.Book;
import com.tiep.bookservice.command.data.BookRepository;
import com.tiep.bookservice.query.model.BookResponseModel;
import com.tiep.bookservice.query.queries.GetAllBooksQuery;
import com.tiep.bookservice.query.queries.GetBookQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// Nơi chứa những @QueryHandler tương ứng được kích hoạt từ Controller
@Component
public class BookProjection {
    @Autowired
    private BookRepository bookRepository;

    @QueryHandler
    public BookResponseModel handle(GetBookQuery query) {
        Book book = bookRepository.getById(query.getBookId());
        BookResponseModel model = new BookResponseModel();
        BeanUtils.copyProperties(book, model);
        return model;
    }

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBooksQuery query) {
        List<BookResponseModel> listModel = new ArrayList<>();
        List<Book> listBook = bookRepository.findAll();
        listBook.forEach(book -> {
            BookResponseModel model = new BookResponseModel();
            BeanUtils.copyProperties(book, model);
            listModel.add(model);
        });
        return listModel;
    }
}
