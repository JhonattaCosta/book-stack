package dev.bookstack.domain.entities;

import dev.bookstack.domain.entities.valueObjects.Isbn;
import dev.bookstack.domain.exceptions.BookInvalidException;

import java.time.LocalDateTime;

public class Book {

    private final Long id;
    private final String title;
    private final String author;
    private final String category;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Isbn isbn;

    public Book(Long id, String title, String author, String category, LocalDateTime createdAt, LocalDateTime updatedAt, Isbn isbn) {

        if (title == null || title.isBlank()){
            throw new BookInvalidException("Title cannot be null or empty!","TITLE_NOT_NULL");
        }
        if (author == null || author.isBlank()){
            throw new BookInvalidException("Author cannot be null or empty!","AUTHOR_NOT_NULL");
        }
        if (category == null || category.isBlank()){
            throw new BookInvalidException("Category cannot be null or empty!","CATEGORY_NOT_NULL");
        }
        if (createdAt == null){
            throw new BookInvalidException("Title cannot be null or empty!","CREATEDAT_NOT_NULL");
        }
        if (updatedAt == null){
            throw new BookInvalidException("Title cannot be null or empty!","UPDATEDAT_NOT_NULL");
        }


        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Isbn getIsbn() {
        return isbn;
    }
}
