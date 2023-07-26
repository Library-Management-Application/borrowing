package com.library.management.borrowing.web.mappers;


import com.library.management.borrowing.domain.Member;
import com.library.management.borrowing.model.MemberDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(uses = {DateMapper.class, BookBorrowedMapper.class})

@DecoratedWith(MemberMapperDecorator.class)
public interface MemberMapper {

    MemberDto memberToMemberDto(Member member);

    Member memberDtoToMember(MemberDto dto);

    void memberDtoToMember(MemberDto memberDto, @MappingTarget Member member);
}