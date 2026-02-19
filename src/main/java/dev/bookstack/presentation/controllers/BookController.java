package dev.bookstack.presentation.controllers;

import dev.bookstack.application.dto.request.CreateBookRequestDto;
import dev.bookstack.application.dto.request.UpdateBookRequestDto;
import dev.bookstack.application.dto.response.BookResponseDto;
import dev.bookstack.application.mappers.BookMapper;
import dev.bookstack.application.usecases.book.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bookStack/book")
@RequiredArgsConstructor
public class BookController {

    private final CreateBookUseCase createBookUseCase;
    private final FindAllBookUseCase findAllBookUseCase;
    private final FindBookByIdUseCase findBookByIdUseCase;
    private final FindBookByTitleUseCase findBookByTitleUseCase;
    private final FindBookByAuthorUseCase findBookByAuthorUseCase;
    private final FindBookByCategoryUseCase findBookByCategoryUseCase;
    private final UpdateBookUseCase updateBookUseCase;
    private final DeleteBookByIdUseCase deleteBookByIdUseCase;



    @PostMapping("/api/books")
    public ResponseEntity<BookResponseDto> createBook(@RequestBody CreateBookRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(createBookUseCase.execute(requestDto));
    }

    @GetMapping("/api/books")
    public ResponseEntity<List<BookResponseDto>> findAllBooks(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(findAllBookUseCase.execute());
    }

    @GetMapping("/api/books/{id}")
    public ResponseEntity<BookResponseDto> findById (@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(findBookByIdUseCase.execute(id));
    }

    @GetMapping("/api/books/search/title")
    public ResponseEntity<List<BookResponseDto>> findByTitle (@RequestParam("title") String title){
        return ResponseEntity.status(HttpStatus.FOUND).body(findBookByTitleUseCase.execute(title));
    }

    @GetMapping("/api/books/search/author")
    public ResponseEntity<List<BookResponseDto>> findByAuthor (@RequestParam("author") String author){
        return ResponseEntity.status(HttpStatus.FOUND).body(findBookByAuthorUseCase.execute(author));
    }

    @GetMapping("/api/books/search/category")
    public ResponseEntity<List<BookResponseDto>> findByCategory (@RequestParam("category") String category){
        return ResponseEntity.status(HttpStatus.FOUND).body(findBookByCategoryUseCase.execute(category));
    }

    @PatchMapping("/api/books/{id}")
    public ResponseEntity<BookResponseDto> updated (@PathVariable Long id, @RequestBody UpdateBookRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateBookUseCase.execute(id,requestDto));
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Void> deleteBook (@PathVariable Long id){
        deleteBookByIdUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }



}
