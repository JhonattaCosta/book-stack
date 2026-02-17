package dev.bookstack.application.usecases.book;

import dev.bookstack.domain.entities.Book;
import dev.bookstack.domain.entities.valueObjects.Isbn;
import dev.bookstack.domain.exceptions.BookNotFoundException;
import dev.bookstack.domain.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteBookUseCaseTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private DeleteBookByIdUseCase deleteBookByIdUseCase;

    @Test
    void shouldDeleteBookById(){
        String isbnString = "9788533302273";
        Isbn isbn = new Isbn(isbnString);
        Book book = new Book(1L, "O Hobbit", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(), isbn);
        Long id = 1L;



        when(repository.findById(id)).thenReturn(Optional.of(book));

        deleteBookByIdUseCase.execute(id);

        verify(repository).delete(id);

    }

    @Test
    void shouldThrowException_WhenBookNotFound(){
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(()->{
            deleteBookByIdUseCase.execute(id);
        }).isInstanceOf(BookNotFoundException.class)
                .hasMessage("Book not found! ");
    }

}
