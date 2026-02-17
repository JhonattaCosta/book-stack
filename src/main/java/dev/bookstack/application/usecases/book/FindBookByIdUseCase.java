package dev.bookstack.application.usecases.book;

import dev.bookstack.application.dto.response.BookResponseDto;
import dev.bookstack.application.mappers.BookMapper;
import dev.bookstack.domain.entities.Book;
import dev.bookstack.domain.exceptions.BookNotFoundException;
import dev.bookstack.domain.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindBookByIdUseCase {

    private final BookRepository repository;

    public FindBookByIdUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public BookResponseDto execute(Long id){
        Optional<Book> bookOpt =  repository.findById(id);
        Book book = bookOpt.orElseThrow(()->
                new BookNotFoundException("Book not found! ","BOOK_NOT_FOUND_BY_ID")
        );
        return BookMapper.toResponse(book);

    }
}
