package dev.bookstack.infrastructure.persistence.mappers;

import dev.bookstack.domain.entities.Book;
import dev.bookstack.domain.entities.valueObjects.Isbn;
import dev.bookstack.infrastructure.persistence.entities.BookEntity;

public class BookEntityMapper {


    public static BookEntity toEntity(Book book){
        BookEntity entity = new BookEntity();

            entity.setId(book.getId());
            entity.setTitle(book.getTitle());
            entity.setAuthor(book.getAuthor());
            entity.setCategory(book.getCategory());
            entity.setCreatedAt(book.getCreatedAt());
            entity.setUpdatedAt(book.getUpdatedAt());
            entity.setIsbn(book.getIsbn().getValue());

            return entity;
    }

    public static Book toDomain(BookEntity entity){
        Book book = new Book(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getCategory(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                new Isbn(entity.getIsbn())
        );
        return book;
    }
}
