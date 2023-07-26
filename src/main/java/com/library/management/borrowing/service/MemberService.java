package com.library.management.borrowing.service;


import com.library.management.borrowing.domain.BookBorrowed;
import com.library.management.borrowing.domain.Member;
import com.library.management.borrowing.model.BookBorrowedDto;
import com.library.management.borrowing.model.MemberDto;
import com.library.management.borrowing.model.MemberWithBooksBorrowedDto;
import com.library.management.borrowing.repo.BookBorrowedRepository;
import com.library.management.borrowing.repo.MemberRepository;
import com.library.management.borrowing.web.mappers.BookBorrowedMapper;
import com.library.management.borrowing.web.mappers.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final BookBorrowedRepository bookBorrowedRepository;
    private final MemberMapper memberMapper;
    private final BookBorrowedMapper bookBorrowedMapper;

    // Get member details
    public MemberWithBooksBorrowedDto findMember(Long memberId) {

        // get the member info
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(memberId.toString()));

        MemberWithBooksBorrowedDto memberWithBooksBorrowedDto = new MemberWithBooksBorrowedDto();
        memberWithBooksBorrowedDto.setMember(memberMapper.memberToMemberDto(member));

        // get the books borrowed by the member
        memberWithBooksBorrowedDto.setBooksBorrowed(findAllCheckedOutBooksByMember(memberId));

        return memberWithBooksBorrowedDto;
    }

    // Add new member
    public MemberDto addNewMember(MemberDto memberDto) {
        Member member = memberMapper.memberDtoToMember(memberDto);

        Member memberSaved = memberRepository.save(member);
        return memberMapper.memberToMemberDto(memberSaved);
    }

    // Update member
    public MemberDto updateMember(MemberDto memberDto) {
        Member memberFound = memberRepository.findById(memberDto.getId()).orElseThrow(() -> new EntityNotFoundException(memberDto.getId().toString()));
        memberMapper.memberDtoToMember(memberDto, memberFound);
        Member memberSaved = memberRepository.save(memberFound);
        return memberMapper.memberToMemberDto(memberSaved);
    }

    // Remove member
    public void removeMember(Long memberId) {
        Member memberFound = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException(memberId.toString()));
        memberRepository.delete(memberFound);
    }

    public List<BookBorrowedDto> checkoutBook(Long memberId, List<BookBorrowedDto> bookBorrowedDtos) {
        // see if member exists
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(memberId.toString()));

        List<BookBorrowedDto> bookBorrowedDtosAfterSave = new ArrayList<>();

        for (BookBorrowedDto bookBorrowedDto: bookBorrowedDtos) {
            BookBorrowed bookBorrowed = bookBorrowedMapper.bookBorrowedDtoToBookBorrowed(bookBorrowedDto);
            bookBorrowed.setMember(member);
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
