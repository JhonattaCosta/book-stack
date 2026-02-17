package dev.bookstack.application.usecases.book;

import dev.bookstack.application.dto.response.BookResponseDto;
import dev.bookstack.domain.entities.Book;
import dev.bookstack.domain.entities.valueObjects.Isbn;
import dev.bookstack.domain.exceptions.BookNotFoundException;
import dev.bookstack.domain.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FindBookByIdUseCaseTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private FindBookByIdUseCase findBookByIdUseCase;

    @Test
    void shouldFindBookById(){

        String isbnString = "9788533302273";
        Isbn isbn = new Isbn(isbnString);

        Optional<BookResponseDto> bookOpt = Optional.of(new BookResponseDto(1L, "O Hobbit", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(), isbnString));
        Optional<Book> domainBook = Optional.of(new Book(1L, "O Hobbit", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(), isbn));


        when(repository.findById(1L)).thenReturn(domainBook);

        BookResponseDto result = findBookByIdUseCase.execute(1L);

        verify(repository).findById(1L);
        assertThat(result.id()).isEqualTo(bookOpt.get().id());
        assertThat(result.title()).isEqualTo(bookOpt.get().title());
        assertThat(result.author()).isEqualTo(bookOpt.get().author());
        assertThat(result.category()).isEqualTo(bookOpt.get().category());

    }

    @Test
    void shouldThrowException_whenIdNotExists(){
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(()->{
            findBookByIdUseCase.execute(1L);
        }).isInstanceOf(BookNotFoundException.class)
                .hasMessageContaining("Book not found! ","BOOK_NOT_FOUND_BY_ID");
    }




}
