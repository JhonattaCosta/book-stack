package dev.bookstack.application.usecases.book;

import dev.bookstack.application.dto.response.BookResponseDto;
import dev.bookstack.application.mappers.BookMapper;
import dev.bookstack.domain.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllBookUseCase {

    private final BookRepository repository;

    public FindAllBookUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public List<BookResponseDto> execute(){
        return repository.findAll().stream().map(BookMapper::toResponse).toList();
    }
}
