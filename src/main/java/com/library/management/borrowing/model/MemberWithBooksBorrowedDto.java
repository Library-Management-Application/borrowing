package com.library.management.borrowing.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MemberWithBooksBorrowedDto {

    private MemberDto member;
    private List<BookBorrowedDto> booksBorrowed;

}
