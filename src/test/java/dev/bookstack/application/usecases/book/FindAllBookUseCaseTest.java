package dev.bookstack.application.usecases.book;

import dev.bookstack.application.dto.response.BookResponseDto;
import dev.bookstack.domain.entities.Book;
import dev.bookstack.domain.entities.valueObjects.Isbn;
import dev.bookstack.domain.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FindAllBookUseCaseTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private FindAllBookUseCase findAllBookUseCase;

    @Test
    void shouldFindAllBook(){
        String isbnString = "9788533302273";
        Isbn isbn = new Isbn(isbnString);
        String isbnString2 = "9788532529954";
        Isbn isbn2 = new Isbn(isbnString2);
        List<Book> domainList = Arrays.asList(
                new Book(1L, "O Hobbit", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(), isbn),
                new Book(2L,"Harry Potter e a Pedra Filosofal","J.K. Rowling","Fantasia", LocalDateTime.now(),LocalDateTime.now(),isbn2)
        );
        List<BookResponseDto> responseDtoListList = Arrays.asList(
                new BookResponseDto(1L, "O Hobbit", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(), isbnString),
                new BookResponseDto(2L,"Harry Potter e a Pedra Filosofal","J.K. Rowling","Fantasia", LocalDateTime.now(),LocalDateTime.now(),isbnString2)
        );


        when(repository.findAll()).thenReturn(domainList);

        List<BookResponseDto> listBook = findAllBookUseCase.execute();

        assertThat(listBook.size()).isEqualTo(responseDtoListList.size());
        assertThat(listBook.get(0).id()).isEqualTo(responseDtoListList.get(0).id());
    }

}
