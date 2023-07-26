package com.library.management.borrowing.repo;


import com.library.management.borrowing.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
