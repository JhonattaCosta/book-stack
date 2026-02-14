package dev.bookstack.infrastructure.persistence.repositories.book;

import dev.bookstack.infrastructure.persistence.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByTitleContainingIgnoreCase(String title);

    List<BookEntity> findByAuthorContainingIgnoreCase(String author);

    List<BookEntity> findByCategoryContainingIgnoreCase(String category);

    boolean existsByIsbn(String isbn);


}
