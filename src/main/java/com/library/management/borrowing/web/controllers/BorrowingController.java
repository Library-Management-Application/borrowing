package com.library.management.borrowing.web.controllers;


import com.library.management.borrowing.model.BookBorrowedDto;
import com.library.management.borrowing.service.borrowing.BorrowingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping ("/api/v1/borrowing")
@RestController
public class BorrowingController {

    private final BorrowingService memberService;

    @GetMapping("/{memberId}")
    public ResponseEntity<List<BookBorrowedDto>> findAllCheckedOutBooksByMember(@PathVariable("memberId") Long memberId)
    {
        return new ResponseEntity<>(memberService.findAllCheckedOutBooksByMember(memberId), HttpStatus.OK);
    }

    // Checkout books
    @PostMapping("/{memberId}/checkout")
    public ResponseEntity<List<BookBorrowedDto>> checkOutBooks(@PathVariable("memberId") Long memberId, @RequestBody @Validated List<BookBorrowedDto> bookBorrowedDtos)
    {
        return new ResponseEntity<>(memberService.checkoutBook(memberId, bookBorrowedDtos), HttpStatus.OK);
    }

    // Return books
    @PostMapping("/{memberId}/return")
    public ResponseEntity<List<BookBorrowedDto>> returnBooks(@PathVariable("memberId") Long memberId, @RequestBody @Validated List<BookBorrowedDto> bookBorrowedDtos)
    {
        return new ResponseEntity<>(memberService.checkoutBook(memberId, bookBorrowedDtos), HttpStatus.OK);
    }
}
