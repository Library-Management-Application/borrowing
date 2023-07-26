package com.library.management.borrowing.web.mappers;

import com.library.management.borrowing.domain.Member;
import com.library.management.borrowing.model.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class MemberMapperDecorator implements MemberMapper {
    private MemberMapper mapper;

    @Autowired
    public void setMapper(MemberMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public MemberDto memberToMemberDto(Member member) {
       return mapper.memberToMemberDto(member);
    }

    @Override
    public Member memberDtoToMember(MemberDto memberDto) {
        return mapper.memberDtoToMember(memberDto);
    }
}
