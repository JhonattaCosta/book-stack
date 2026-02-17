package dev.bookstack.infrastructure.persistence.repositories.book;

import dev.bookstack.domain.entities.Book;
import dev.bookstack.domain.repositories.BookRepository;
import dev.bookstack.infrastructure.persistence.mappers.BookEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final BookJpaRepository repository;

    public BookRepositoryImpl(BookJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        return BookEntityMapper.toDomain(repository.save(BookEntityMapper.toEntity(book)));
    }

    @Override
    public Optional<Book> findById(Long id) {
      return repository.findById(id).map(BookEntityMapper::toDomain);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll().stream().map(BookEntityMapper::toDomain).toList();
    }

    @Override
    public List<Book> findByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title).stream().map(BookEntityMapper::toDomain).toList();
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return repository.findByAuthorContainingIgnoreCase(author).stream().map(BookEntityMapper::toDomain).toList();
    }

    @Override
    public List<Book> findByCategory(String category) {
        return repository.findByCategoryContainingIgnoreCase(category).stream().map(BookEntityMapper::toDomain).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existByIsbn(String isbn) {
        return repository.existsByIsbn(isbn);
    }
}
