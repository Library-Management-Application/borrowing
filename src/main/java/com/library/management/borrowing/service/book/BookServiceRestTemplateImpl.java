package com.library.management.borrowing.service.book;

import com.library.management.borrowing.service.book.model.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@ConfigurationProperties(prefix = "library", ignoreUnknownFields = true)
@Component
public class BookServiceRestTemplateImpl implements BookService {

    public static final String BOOK_PATH = "/api/v1/book/{bookId}";
    private final RestTemplate restTemplate;

    private String bookServiceHost;

    public void setBookServiceHost(String bookServiceHost) {
        this.bookServiceHost = bookServiceHost;
    }

    public BookServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder,
                                       @Value("${library.book-service.user}") String bookUser,
                                       @Value("${library.book-service.password}")String bookPassword) {
        this.restTemplate = restTemplateBuilder
                .basicAuthentication(bookUser, bookPassword)
                .build();
    }

    @Override
    public Integer getOnhandInventory(Long bookId) {

        log.debug("Calling Book Service");

        ResponseEntity<BookDto> responseEntity = restTemplate
                .exchange(bookServiceHost + BOOK_PATH, HttpMethod.GET, null,
                        new ParameterizedTypeReference<BookDto>(){}, (Object) bookId);



        BookDto bookDto = Objects.requireNonNull(responseEntity.getBody());

        return getQuantityAtHand(bookDto);
    }
    private Integer getQuantityAtHand(BookDto bookDto)
    {

        int currentInventory = bookDto.getQuantity() - (bookDto.getQuantityCheckedOut()== null?0:bookDto.getQuantityCheckedOut()) ;
        return currentInventory;
    }

}
