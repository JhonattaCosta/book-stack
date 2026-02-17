package dev.bookstack.application.usecases;

import dev.bookstack.application.dto.request.CreateBookRequestDto;
import dev.bookstack.domain.entities.Book;
import dev.bookstack.domain.entities.valueObjects.Isbn;
import dev.bookstack.domain.exceptions.BookInvalidException;
import dev.bookstack.domain.exceptions.IsbnAlreadyExistsException;
import dev.bookstack.domain.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class CreateBookUseCaseTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private CreateBookUseCase createBookUseCase;


    String isbnString = "9788533302273";
    Isbn isbn = new Isbn(isbnString);


    @Test
    void shouldCreateBook_Successfully(){


        CreateBookRequestDto request = new CreateBookRequestDto( "O Hobbit", "J. R. R. Tolkien", "Fantasia",isbnString);
        Book createdBook = new Book(1L, "O Hobbit", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(), isbn);

        when(repository.existByIsbn(isbnString)).thenReturn(false);
        when(repository.save(any(Book.class))).thenReturn(createdBook);

        Book result = createBookUseCase.execute(request);

        verify(repository).save(any(Book.class));
        assertThat(result).isEqualTo(createdBook);
    }

    @Test
    void shouldThrowException_WhenTitleIsNull(){

        CreateBookRequestDto request = new CreateBookRequestDto( null, "J. R. R. Tolkien", "Fantasia",isbnString);

        assertThatThrownBy(()->{
            createBookUseCase.execute(request);
        }).isInstanceOf(BookInvalidException.class)
                .hasMessageContaining("Title cannot be null or empty!", "TITLE_NOT_NULL");

    }
    @Test
    void shouldThrowException_WhenTitleIsEmpty(){

        CreateBookRequestDto request = new CreateBookRequestDto( "", "J. R. R. Tolkien", "Fantasia",isbnString);

        assertThatThrownBy(()->{
            createBookUseCase.execute(request);
        }).isInstanceOf(BookInvalidException.class)
                .hasMessageContaining("Title cannot be null or empty!", "TITLE_NOT_NULL");

    }

    @Test
    void shouldThrowException_WhenTitleIsBlank(){

        CreateBookRequestDto request = new CreateBookRequestDto( " ", "J. R. R. Tolkien", "Fantasia",isbnString);

        assertThatThrownBy(()->{
            createBookUseCase.execute(request);
        }).isInstanceOf(BookInvalidException.class)
                .hasMessageContaining("Title cannot be null or empty!", "TITLE_NOT_NULL");

    }


    @Test
    void shouldThrowException_WhenAuthorIsNull(){

        CreateBookRequestDto request = new CreateBookRequestDto( "O Hobbit", null, "Fantasia",isbnString);

        assertThatThrownBy(()->{
            createBookUseCase.execute(request);
        }).isInstanceOf(BookInvalidException.class)
                .hasMessageContaining("Author cannot be null or empty!", "AUTHOR_NOT_NULL");

    }
    @Test
    void shouldThrowException_WhenAuthorIsEmpty(){

        CreateBookRequestDto request = new CreateBookRequestDto( "O Hobbit", "", "Fantasia",isbnString);

        assertThatThrownBy(()->{
            createBookUseCase.execute(request);
        }).isInstanceOf(BookInvalidException.class)
                .hasMessageContaining("Author cannot be null or empty!", "AUTHOR_NOT_EMPTY");

    }

    @Test
    void shouldThrowException_WhenAuthorIsBlank(){

        CreateBookRequestDto request = new CreateBookRequestDto( "O Hobbit", " ", "Fantasia",isbnString);

        assertThatThrownBy(()->{
            createBookUseCase.execute(request);
        }).isInstanceOf(BookInvalidException.class)
                .hasMessageContaining("Author cannot be null or empty!", "AUTHOR_NOT_BLANK");

    }

    @Test
    void shouldThrowException_WhenCategoryIsNull(){

        CreateBookRequestDto request = new CreateBookRequestDto( "O Hobbit", "J. R. R. Tolkien", null ,isbnString);

        assertThatThrownBy(()->{
            createBookUseCase.execute(request);
        }).isInstanceOf(BookInvalidException.class)
                .hasMessageContaining("Category cannot be null or empty!", "CATEGORY_NOT_NULL");

    }
    @Test
    void shouldThrowException_WhenCategoryIsEmpty(){

        CreateBookRequestDto request = new CreateBookRequestDto( "O Hobbit", "J. R. R. Tolkien", "",isbnString);

        assertThatThrownBy(()->{
            createBookUseCase.execute(request);
        }).isInstanceOf(BookInvalidException.class)
                .hasMessageContaining("Category cannot be null or empty!", "CATEGORY_NOT_EMPTY");

    }

    @Test
    void shouldThrowException_WhenCategoryIsBlank(){

        CreateBookRequestDto request = new CreateBookRequestDto( "O Hobbit", "J. R. R. Tolkien", " ",isbnString);

        assertThatThrownBy(()->{
            createBookUseCase.execute(request);
        }).isInstanceOf(BookInvalidException.class)
                .hasMessageContaining("Category cannot be null or empty!", "CATEGORY_NOT_BLANK");

    }



    @Test
    void shouldThrowException_WhenIsbnAlreadyExists(){
        CreateBookRequestDto request = new CreateBookRequestDto( "O Hobbit", "J. R. R. Tolkien", " ",isbnString);
        when(repository.existByIsbn(request.isbn())).thenReturn(true);

        assertThatThrownBy(()->{
            createBookUseCase.execute(request);
        }).isInstanceOf(IsbnAlreadyExistsException.class)
                .hasMessageContaining("ISBN already exists try again", "ISBN_ALREADY_EXISTS");


    }

}
