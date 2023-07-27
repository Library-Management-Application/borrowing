package com.library.management.borrowing.service.borrowing;


import com.library.management.borrowing.domain.BookBorrowed;
import com.library.management.borrowing.model.BookBorrowedDto;
import com.library.management.borrowing.repo.BookBorrowedRepository;
import com.library.management.borrowing.web.mappers.BookBorrowedMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class BorrowingService {

    private final BookBorrowedRepository bookBorrowedRepository;
    private final BookBorrowedMapper bookBorrowedMapper;

    public List<BookBorrowedDto> checkoutBook(Long memberId, List<BookBorrowedDto> bookBorrowedDtos) {

        List<BookBorrowedDto> bookBorrowedDtosAfterSave = new ArrayList<>();

        for (BookBorrowedDto bookBorrowedDto: bookBorrowedDtos) {
            BookBorrowed bookBorrowed = bookBorrowedMapper.bookBorrowedDtoToBookBorrowed(bookBorrowedDto);
            bookBorrowed.setMemberId(memberId);
            BookBorrowed bookBorrowedSaved = bookBorrowedRepository.save(bookBorrowed);
            bookBorrowedDtosAfterSave.add(bookBorrowedMapper.bookBorrowedToBookBorrowedDto(bookBorrowedSaved));
        }
        return bookBorrowedDtosAfterSave;
    }

    public List<BookBorrowedDto> findAllCheckedOutBooksByMember(Long memberId) {
        List<BookBorrowedDto> bookBorrowedDtosList = new ArrayList<>();
        List<BookBorrowed> bookBorrowedList = bookBorrowedRepository.findByMemberId(memberId)
                .orElse(Collections.EMPTY_LIST);
        if (bookBorrowedList.isEmpty())
            return  bookBorrowedDtosList;

        for (BookBorrowed bookBorrowed: bookBorrowedList)
        {
            bookBorrowedDtosList.add(bookBorrowedMapper.bookBorrowedToBookBorrowedDto(bookBorrowed));
        }
        return bookBorrowedDtosList;
    }
}
