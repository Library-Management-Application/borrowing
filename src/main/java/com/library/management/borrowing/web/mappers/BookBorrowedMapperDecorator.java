package com.library.management.borrowing.web.mappers;

import com.library.management.borrowing.domain.BookBorrowed;
import com.library.management.borrowing.model.BookBorrowedDto;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BookBorrowedMapperDecorator implements BookBorrowedMapper {
    private BookBorrowedMapper mapper;

    @Autowired
    public void setMapper(BookBorrowedMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BookBorrowedDto bookBorrowedToBookBorrowedDto(BookBorrowed bookBorrowed) {
       return mapper.bookBorrowedToBookBorrowedDto(bookBorrowed);
    }

    @Override
    public BookBorrowed bookBorrowedDtoToBookBorrowed(BookBorrowedDto bookBorrowedDto) {
        return mapper.bookBorrowedDtoToBookBorrowed(bookBorrowedDto);
    }
}
