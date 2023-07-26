package com.library.management.borrowing.repo;


import com.library.management.borrowing.domain.BookBorrowed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookBorrowedRepository extends JpaRepository<BookBorrowed, Long> {

    Optional<List<BookBorrowed>> findByMemberId(Long borrowerId);
}
