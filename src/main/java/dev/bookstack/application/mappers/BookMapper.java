package dev.bookstack.application.mappers;

import dev.bookstack.application.dto.request.CreateBookRequestDto;
import dev.bookstack.application.dto.request.UpdateBookRequestDto;
import dev.bookstack.application.dto.response.BookResponseDto;
import dev.bookstack.domain.entities.Book;
import dev.bookstack.domain.entities.valueObjects.Isbn;

import java.time.LocalDateTime;

public class BookMapper {

    public static Book toDomain(CreateBookRequestDto request){
        Book book = new Book(
                null,
                request.title(),
                request.author(),
                request.category(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                new Isbn(request.isbn())
        );

        return book;
    }

    public static Book toUpdateDomain(UpdateBookRequestDto request){
        Book book = new Book(
                null,
                request.title(),
                request.author(),
                request.category(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                new Isbn(request.isbn())
        );
        return book;
    }

    public static BookResponseDto toResponse(Book book){
        BookResponseDto response = new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getCreatedAt(),
                book.getUpdatedAt(),
                new Isbn(book.getIsbn().getValue())

        );

        return response;
    }

}
