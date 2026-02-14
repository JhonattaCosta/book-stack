package dev.bookstack.domain.repositories;

import dev.bookstack.domain.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> findById(Long id);

    List<Book> findAll();

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByCategory(String category);

    void delete(Long id);

    boolean existByIsbn(String isbn);


}
