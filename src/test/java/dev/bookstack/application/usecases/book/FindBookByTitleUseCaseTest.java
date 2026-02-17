package dev.bookstack.application.usecases.book;

import dev.bookstack.application.dto.response.BookResponseDto;
import dev.bookstack.domain.entities.Book;
import dev.bookstack.domain.entities.valueObjects.Isbn;
import dev.bookstack.domain.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FindBookByTitleUseCaseTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private FindBookByTitleUseCase findBookByTitleUseCase;

    @Test
    void shouldShowAllBookFromSelectedTitle(){
        String isbn1 = "9788595084759";
        Isbn isbn = new Isbn(isbn1);
        String isbn2 = "9788595084766";
        Isbn isbnH = new Isbn(isbn2);

        List<Book> domainList = Arrays.asList(
                new Book(1L, "O Senhor dos Anéis: A Sociedade do Anel", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(), isbn),
                new Book(2L,"O Senhor dos Anéis: As Duas Torres","J. R. R. Tolkien","Fantasia", LocalDateTime.now(),LocalDateTime.now(),isbnH)
        );
        List<BookResponseDto> responseDtoList = Arrays.asList(
                new BookResponseDto(1L, "O Senhor dos Anéis: A Sociedade do Anel", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(),"9788595084759"),
                new BookResponseDto(2L,"O Senhor dos Anéis: As Duas Torres","J. R. R. Tolkien","Fantasia", LocalDateTime.now(),LocalDateTime.now(),"9788595084766")
        );

        when(repository.findByTitle("O Senhor")).thenReturn(domainList);

        List<BookResponseDto> resultList = findBookByTitleUseCase.execute("O Senhor");

        assertThat(resultList.size()).isEqualTo(responseDtoList.size());
        assertThat(resultList.get(0).id()).isEqualTo(responseDtoList.get(0).id());

    }


}
