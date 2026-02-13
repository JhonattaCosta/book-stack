package dev.bookstack.domain.entities;

import dev.bookstack.domain.entities.valueObjects.Isbn;
import dev.bookstack.domain.exceptions.BookInvalidException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;


public class BookTest {

    String isbn1 = "9788533302273";
    Isbn isbn = new Isbn(isbn1);

    @Test
    void shouldCreateBook_WhenAllFieldsAreValid() {
        String title = "O Hobbit";
        String author = "J. R. R. Tolkien";
        String category = "Fantasia";

                Book book = new Book(null, "O Hobbit", "J. R. R. Tolkien", "Fantasia",LocalDateTime.now(),LocalDateTime.now(), isbn);

        assertThat(book.getTitle()).isEqualTo(title);
        assertThat(book.getAuthor()).isEqualTo(author);
        assertThat(book.getCategory()).isEqualTo(category);
        assertThat(book.getIsbn()).isEqualTo(isbn);
        assertThat(book.getCreatedAt()).isNotNull();
        assertThat(book.getCreatedAt()).isBefore(LocalDateTime.now().plusSeconds(1));
        assertThat(book.getUpdatedAt()).isNotNull();
        assertThat(book.getUpdatedAt()).isEqualTo(book.getCreatedAt());
    }

    @Test
    void shouldThrowException_WhenTitleIsNull() {


        assertThatThrownBy(() -> {
            new Book(null, null, "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(),isbn);
        })
                .isInstanceOf(BookInvalidException.class)
                .hasMessage("Title cannot be null or empty!");
    }

    @Test
    void shouldThrowException_WhenTitleIsBlank() {

        assertThatThrownBy(() -> {
            new Book(null, " ", "J. R. R. Tolkien", "Fantasia", LocalDateTime.now(),LocalDateTime.now(),isbn);
        })
                .isInstanceOf(BookInvalidException.class)
                .hasMessage("Title cannot be null or empty!");
    }

    @Test
    void shouldThrowException_WhenAuthorIsNull() {

        assertThatThrownBy(()->{
            new Book(null, "O Hobbit",null, "Fantasia",LocalDateTime.now(),LocalDateTime.now(), isbn);
        })
                .isInstanceOf(BookInvalidException.class)
                .hasMessage("Author cannot be null or empty!");

    }

    @Test
    void shouldThrowException_WhenAuthorIsBlank() {
        assertThatThrownBy(()->{
            new Book(null, "O Hobbit"," ", "Fantasia", LocalDateTime.now(),LocalDateTime.now(),isbn);
        })
                .isInstanceOf(BookInvalidException.class)
                .hasMessage("Author cannot be null or empty!");
    }

    @Test
    void shouldThrowException_WhenCategoryIsNull() {
        assertThatThrownBy(()->{
            new Book(null, "O Hobbit","J. R. R. Tolkien", null,LocalDateTime.now(),LocalDateTime.now(), isbn);
        })
                .isInstanceOf(BookInvalidException.class)
                .hasMessage("Category cannot be null or empty!");
    }

    @Test
    void shouldThrowException_WhenCategoryIsBlank() {
        assertThatThrownBy(()->{
            new Book(null, "O Hobbit","J. R. R. Tolkien", " ", LocalDateTime.now(),LocalDateTime.now(),isbn);
        })
                .isInstanceOf(BookInvalidException.class)
                .hasMessage("Category cannot be null or empty!");
    }

}
