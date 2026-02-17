package dev.bookstack.application.usecases.book;

import dev.bookstack.domain.entities.Book;
import dev.bookstack.domain.exceptions.BookNotFoundException;
import dev.bookstack.domain.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteBookByIdUseCase {

    private final BookRepository repository;

    public DeleteBookByIdUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        repository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("Book not found! ","BOOK_NOT_FOUND_BY_ID"));

        repository.delete(id);
    }
}
