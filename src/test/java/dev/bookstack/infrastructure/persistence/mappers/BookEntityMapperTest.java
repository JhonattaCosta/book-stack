package dev.bookstack.infrastructure.persistence.mappers;

import dev.bookstack.domain.entities.Book;
import dev.bookstack.domain.entities.valueObjects.Isbn;
import dev.bookstack.infrastructure.persistence.entities.BookEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

public class BookEntityMapperTest {

    @Test
    void shoudConverterBook_ToBookEntity() {

        Book book = new Book(1L, "O Hobbit", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(), LocalDateTime.now(), new Isbn("9788533302273"));

        BookEntity entity = BookEntityMapper.toEntity(book);

        assertThat(entity.getId()).isEqualTo(book.getId());
        assertThat(entity.getTitle()).isEqualTo(book.getTitle());
        assertThat(entity.getAuthor()).isEqualTo(book.getAuthor());
        assertThat(entity.getCategory()).isEqualTo(book.getCategory());
        assertThat(entity.getCreatedAt()).isEqualTo(book.getCreatedAt());
        assertThat(entity.getUpdatedAt()).isEqualTo(book.getUpdatedAt());
        assertThat(entity.getIsbn()).isEqualTo(book.getIsbn().getValue());

    }

    @Test
    void sholdConverterBookEntity_ToBook(){

        BookEntity entity = new BookEntity();
        entity.setId(1L);
        entity.setTitle("O Hobbit");
        entity.setAuthor("J. R. R. Tolkien");
        entity.setCategory( "Fantasia");
        entity.setCreatedAt( LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setIsbn("9788533302273");

        Book book = BookEntityMapper.toDomain(entity);

        assertThat(book.getId()).isEqualTo(entity.getId());
        assertThat(book.getTitle()).isEqualTo(entity.getTitle());
        assertThat(book.getAuthor()).isEqualTo(entity.getAuthor());
        assertThat(book.getCategory()).isEqualTo(entity.getCategory());
        assertThat(book.getCreatedAt()).isEqualTo(entity.getCreatedAt());
        assertThat(book.getUpdatedAt()).isEqualTo(entity.getUpdatedAt());
        assertThat(book.getIsbn().getValue()).isEqualTo(entity.getIsbn());
    }

}
