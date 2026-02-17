package dev.bookstack.application.usecases.book;

import dev.bookstack.application.dto.request.UpdateBookRequestDto;
import dev.bookstack.application.dto.response.BookResponseDto;
import dev.bookstack.application.mappers.BookMapper;
import dev.bookstack.domain.entities.Book;
import dev.bookstack.domain.entities.valueObjects.Isbn;
import dev.bookstack.domain.exceptions.BookNotFoundException;
import dev.bookstack.domain.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateBookUseCaseTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private UpdateBookUseCase updateBookUseCase;

    @Test
    void shouldUpdatedBook(){
        String isbnString = "9788533302273";
        Isbn isbn = new Isbn(isbnString);

        Book domainBook = new Book(1L, "O Hobbitt", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(), isbn);
        UpdateBookRequestDto bookUpdated = new UpdateBookRequestDto("O Hobbit", "J. R. R. Tolkien", "Fantasia", isbnString );
        Book updatedDomainBook = new Book(1L, "O Hobbit", "J. R. R. Tolkien", "Fantasia", domainBook.getCreatedAt(),LocalDateTime.now(), isbn);

        when(repository.findById(1L)).thenReturn(Optional.of(domainBook));
        when(repository.save(any(Book.class))).thenReturn(updatedDomainBook);

        BookResponseDto result = updateBookUseCase.execute(1L, bookUpdated);



        verify(repository).save(any(Book.class));
        assertThat(result.title()).isEqualTo(bookUpdated.title());
        assertThat(result.author()).isEqualTo(bookUpdated.author());
        assertThat(result.category()).isEqualTo(bookUpdated.category());
        assertThat(result.isbn()).isEqualTo(bookUpdated.isbn());


    }

}
