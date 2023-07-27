package com.library.management.borrowing.web.mappers;


import com.library.management.borrowing.domain.BookBorrowed;
import com.library.management.borrowing.model.BookBorrowedDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BookBorrowedMapperDecorator.class)
public interface BookBorrowedMapper {

    BookBorrowedDto bookBorrowedToBookBorrowedDto(BookBorrowed bookBorrowed);

    BookBorrowed bookBorrowedDtoToBookBorrowed(BookBorrowedDto dto);

    void bookBorrowedDtoToBookBorrowed(BookBorrowedDto bookBorrowedDto, @MappingTarget BookBorrowed bookBorrowedFound);
}