package dev.bookstack.infrastructure.persistence.repositories.book;

import dev.bookstack.domain.entities.Book;
import dev.bookstack.domain.entities.valueObjects.Isbn;
import dev.bookstack.infrastructure.persistence.entities.BookEntity;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookRepositoryImplTest {

    @Mock
    private BookJpaRepository repositoryJpa;

    @InjectMocks
    private BookRepositoryImpl bookRepository;


    @Test
    void shouldSaveBook_Successfully(){
        String isbn1 = "9788533302273";
        Isbn isbn = new Isbn(isbn1);

        Book domainBook = new Book(1L, "O Hobbit", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(), isbn);
        BookEntity savedBook = new BookEntity(1L, "O Hobbit", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(),isbn.getValue());

        when(repositoryJpa.save(any(BookEntity.class))).thenReturn(savedBook);

       Book result = bookRepository.save(domainBook);

       verify(repositoryJpa).save(any(BookEntity.class));
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(domainBook.getTitle());

    }

    @Test
    void shouldFindBookById(){
        String isbn1 = "9788533302273";
        Isbn isbn = new Isbn(isbn1);
        Optional<Book> domainBook = Optional.of(new Book(1L, "O Hobbit", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(), isbn));
        Optional<BookEntity> entityBook = Optional.of(new BookEntity(1L, "O Hobbit", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(), "9788533302273"));

        when(repositoryJpa.findById(1L)).thenReturn(entityBook);


        Optional<Book> result = bookRepository.findById(1L);


        assertThat(result.isPresent());
        assertThat(result.get().getId()).isEqualTo(domainBook.get().getId());
    }

    @Test
    void shouldShowAllBooks(){
        String isbn1 = "9788533302273";
        Isbn isbn = new Isbn(isbn1);
        String isbn2 = "9788532529954";
        Isbn isbnH = new Isbn(isbn2);
        List<Book> domainList = Arrays.asList(
                new Book(1L, "O Hobbit", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(), isbn),
                new Book(2L,"Harry Potter e a Pedra Filosofal","J.K. Rowling","Fantasia", LocalDateTime.now(),LocalDateTime.now(),isbnH)
        );
        List<BookEntity> entityList = Arrays.asList(
                new BookEntity(1L, "O Hobbit", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(),"9788533302273"),
                new BookEntity(2L,"Harry Potter e a Pedra Filosofal","J.K. Rowling","Fantasia", LocalDateTime.now(),LocalDateTime.now(),"9788532529954")
        );

        when(repositoryJpa.findAll()).thenReturn(entityList);

        List<Book> result = bookRepository.findAll();

        assertThat(result.size()).isEqualTo(domainList.size());
        assertThat(result.get(0).getId()).isEqualTo(domainList.get(0).getId());
    }

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
        List<BookEntity> entityList = Arrays.asList(
                new BookEntity(1L, "O Senhor dos Anéis: A Sociedade do Anel", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(),"9788595084759"),
                new BookEntity(2L,"O Senhor dos Anéis: As Duas Torres","J. R. R. Tolkien","Fantasia", LocalDateTime.now(),LocalDateTime.now(),"9788595084766")
        );

        when(repositoryJpa.findByTitleContainingIgnoreCase("O Senhor")).thenReturn(entityList);

        List<Book> result = bookRepository.findByTitle("O Senhor");

        assertThat(result.size()).isEqualTo(domainList.size());
        assertThat(result.get(0).getId()).isEqualTo(domainList.get(0).getId());

    }

    @Test
    void shouldShowAllBookFromSelectedAuthor(){
        String isbn1 = "9788595084759";
        Isbn isbn = new Isbn(isbn1);
        String isbn2 = "9788595084766";
        Isbn isbnH = new Isbn(isbn2);

        List<Book> domainList = Arrays.asList(
                new Book(1L, "O Senhor dos Anéis: A Sociedade do Anel", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(), isbn),
                new Book(2L,"O Senhor dos Anéis: As Duas Torres","J. R. R. Tolkien","Fantasia", LocalDateTime.now(),LocalDateTime.now(),isbnH)
        );
        List<BookEntity> entityList = Arrays.asList(
                new BookEntity(1L, "O Senhor dos Anéis: A Sociedade do Anel", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(),"9788595084759"),
                new BookEntity(2L,"O Senhor dos Anéis: As Duas Torres","J. R. R. Tolkien","Fantasia", LocalDateTime.now(),LocalDateTime.now(),"9788595084766")
        );

        when(repositoryJpa.findByAuthorContainingIgnoreCase("J.J.R")).thenReturn(entityList);

        List<Book>result = bookRepository.findByAuthor("J.J.R");

        assertThat(result.size()).isEqualTo(domainList.size());
        assertThat(result.get(0).getId()).isEqualTo(domainList.get(0).getId());
    }

    @Test
    void shouldShowAllBookFromSelectedCategory(){
        String isbn1 = "9788595084759";
        Isbn isbn = new Isbn(isbn1);
        String isbn2 = "9788595084766";
        Isbn isbnH = new Isbn(isbn2);

        List<Book> domainList = Arrays.asList(
                new Book(1L, "O Senhor dos Anéis: A Sociedade do Anel", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(), isbn),
                new Book(2L,"O Senhor dos Anéis: As Duas Torres","J. R. R. Tolkien","Fantasia", LocalDateTime.now(),LocalDateTime.now(),isbnH)
        );
        List<BookEntity> entityList = Arrays.asList(
                new BookEntity(1L, "O Senhor dos Anéis: A Sociedade do Anel", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(),"9788595084759"),
                new BookEntity(2L,"O Senhor dos Anéis: As Duas Torres","J. R. R. Tolkien","Fantasia", LocalDateTime.now(),LocalDateTime.now(),"9788595084766")
        );

        when(repositoryJpa.findByCategoryContainingIgnoreCase("Fantasia")).thenReturn(entityList);

        List<Book>result = bookRepository.findByCategory("Fantasia");

        assertThat(result.size()).isEqualTo(domainList.size());
        assertThat(result.get(0).getId()).isEqualTo(domainList.get(0).getId());

    }

    @Test
    void shouldDeleteBookById(){
        Long bookId = 1L;

        bookRepository.delete(bookId);

        verify(repositoryJpa).deleteById(1L);
    }

    @Test
    void shouldReturnTrue_WhenIsbnExist(){

        when(repositoryJpa.existsByIsbn("9788533302273")).thenReturn(true);

        boolean result = bookRepository.existByIsbn("9788533302273");

        verify(repositoryJpa).existsByIsbn("9788533302273");

        assertThat(result).isTrue();

    }

}
