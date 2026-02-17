package dev.bookstack.application.usecases;

import dev.bookstack.application.dto.request.CreateBookRequestDto;
import dev.bookstack.application.mappers.BookMapper;
import dev.bookstack.domain.entities.Book;
import dev.bookstack.domain.exceptions.IsbnAlreadyExistsException;
import dev.bookstack.domain.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateBookUseCase {

    private final BookRepository repository;

    public CreateBookUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public Book execute(CreateBookRequestDto request){
        if (repository.existByIsbn(request.isbn())){
            throw new IsbnAlreadyExistsException("ISBN already exists try again", "ISBN_ALREADY_EXISTS");
        }
        return repository.save(BookMapper.toDomain(request));
    }

}
