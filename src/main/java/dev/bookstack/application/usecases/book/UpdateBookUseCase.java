package dev.bookstack.application.usecases.book;


import dev.bookstack.application.dto.request.UpdateBookRequestDto;
import dev.bookstack.application.dto.response.BookResponseDto;
import dev.bookstack.application.mappers.BookMapper;
import dev.bookstack.domain.entities.Book;
import dev.bookstack.domain.entities.valueObjects.Isbn;
import dev.bookstack.domain.exceptions.BookNotFoundException;
import dev.bookstack.domain.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UpdateBookUseCase {

    private final BookRepository repository;

    public UpdateBookUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public BookResponseDto execute(Long id, UpdateBookRequestDto request){
        Book book = repository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("Book not found! ","BOOK_NOT_FOUND_BY_ID"));

        Book updatedBook = new Book(
                book.getId(),
                request.title() != null && !request.title().isBlank() ? request.title() : book.getTitle(),
                request.author() != null && !request.author().isBlank() ? request.author() : book.getAuthor(),
                request.category() != null && !request.category().isBlank() ? request.category() : book.getCategory(),
                book.getCreatedAt(),
                LocalDateTime.now(),
                request.isbn() != null && !request.isbn().isBlank() ? new Isbn(request.isbn()) : book.getIsbn()
        );

        return BookMapper.toResponse(repository.save(updatedBook));

    }
}
