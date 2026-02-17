package dev.bookstack.application.mappers;

import dev.bookstack.application.dto.request.CreateBookRequestDto;
import dev.bookstack.application.dto.request.UpdateBookRequestDto;
import dev.bookstack.application.dto.response.BookResponseDto;
import dev.bookstack.domain.entities.Book;
import dev.bookstack.domain.entities.valueObjects.Isbn;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class BookMapperTest {

    @Test
    void shouldConverterCreateBookRequest_ToBook(){

        CreateBookRequestDto request = new CreateBookRequestDto("O Hobbit", "J. R. R. Tolkien", "Fantasia","9788533302273");

        Book book = BookMapper.toDomain(request);


        assertThat(book.getTitle()).isEqualTo(request.title());
        assertThat(book.getAuthor()).isEqualTo(request.author());
        assertThat(book.getCategory()).isEqualTo(request.category());
        assertThat(book.getIsbn().getValue()).isEqualTo(request.isbn());


    }

    @Test
    void shouldConverterUpdateBookRequest_ToBook(){

        Book book = new Book(1L, "O Hobbitt", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(), LocalDateTime.now(), new Isbn("9788533302273"));

        UpdateBookRequestDto request = new UpdateBookRequestDto("O Hobbit", "J. R. R. Tolkien", "Fantasia","9788533302273");

        Book bookUpdated = BookMapper.toUpdateDomain(request);

        assertThat(bookUpdated.getTitle()).isEqualTo(request.title());
        assertThat(bookUpdated.getAuthor()).isEqualTo(request.author());
        assertThat(bookUpdated.getCategory()).isEqualTo(request.category());
        assertThat(bookUpdated.getIsbn().getValue()).isEqualTo(request.isbn());

    }

    @Test
    void shouldConverterBook_ToBookResponseDto(){

        Book book = new Book(1L, "O Hobbitt", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(), LocalDateTime.now(), new Isbn("9788533302273"));

        BookResponseDto response = BookMapper.toResponse(book);

        assertThat(response.id()).isEqualTo(book.getId());
        assertThat(response.title()).isEqualTo(book.getTitle());
        assertThat(response.author()).isEqualTo(book.getAuthor());
        assertThat(response.category()).isEqualTo(book.getCategory());
        assertThat(response.createdAt()).isEqualTo(book.getCreatedAt());
        assertThat(response.updatedAt()).isEqualTo(book.getUpdatedAt());
        assertThat(response.isbn()).isEqualTo(book.getIsbn().getValue());


    }




}
