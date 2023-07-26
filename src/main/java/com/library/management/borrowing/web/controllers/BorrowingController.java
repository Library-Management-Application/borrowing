package com.library.management.borrowing.web.controllers;


import com.library.management.borrowing.model.BookBorrowedDto;
import com.library.management.borrowing.model.MemberDto;
import com.library.management.borrowing.model.MemberWithBooksBorrowedDto;
import com.library.management.borrowing.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping ("/api/v1/member")
@RestController
public class BorrowingController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberWithBooksBorrowedDto> getMember(@PathVariable("memberId") Long memberId)
    {
        return new ResponseEntity<>(memberService.findMember(memberId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MemberDto> addNewMember(@RequestBody @Validated MemberDto memberDto)
    {
        return new ResponseEntity<>(memberService.addNewMember(memberDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MemberDto> updateMember(@RequestBody @Validated MemberDto memberDto)
    {
        return new ResponseEntity<>(memberService.updateMember(memberDto), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{memberId}/booksborrowed")
    public ResponseEntity<List<BookBorrowedDto>> findAllCheckedOutBooksByMember(@PathVariable("memberId") Long memberId)
    {
        return new ResponseEntity<>(memberService.findAllCheckedOutBooksByMember(memberId), HttpStatus.OK);
    }

    @PostMapping("/{memberId}/checkout")
    public ResponseEntity<List<BookBorrowedDto>> checkOutBooks(@PathVariable("memberId") Long memberId, @RequestBody @Validated List<BookBorrowedDto> bookBorrowedDtos)
    {
        return new ResponseEntity<>(memberService.checkoutBook(memberId, bookBorrowedDtos), HttpStatus.OK);
    }
}
