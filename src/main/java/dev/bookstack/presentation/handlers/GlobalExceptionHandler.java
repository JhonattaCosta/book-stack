package dev.bookstack.presentation.handlers;

import dev.bookstack.application.dto.exception.ErrorResponseHandler;
import dev.bookstack.domain.exceptions.BookInvalidException;
import dev.bookstack.domain.exceptions.BookNotFoundException;
import dev.bookstack.domain.exceptions.IsbnAlreadyExistsException;
import dev.bookstack.domain.exceptions.IsbnInvalidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookInvalidException.class)
    public ResponseEntity<ErrorResponseHandler> handlerInvalid(BookInvalidException ex, WebRequest request) {
        ErrorResponseHandler error = new ErrorResponseHandler(
                LocalDateTime.now(),
                400,
                "Book invalid",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                ex.getErrorCode()
        );
        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponseHandler> handlerNotFound(BookNotFoundException ex, WebRequest request) {
        ErrorResponseHandler error = new ErrorResponseHandler(
                LocalDateTime.now(),
                404,
                "Book Not Found",
                ex.getMessage(),
                request.getDescription(false).replace("uri=",""),
                ex.getErrorCode()
        );
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(IsbnAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseHandler> handlerAlreadyExists(IsbnAlreadyExistsException ex, WebRequest request) {
        ErrorResponseHandler error = new ErrorResponseHandler(
                LocalDateTime.now(),
                400,
                "ISBN Already Exists",
                ex.getMessage(),
                request.getDescription(false).replace("uri=",""),
                ex.getErrorCode()
        );
        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(IsbnInvalidException.class)
    public ResponseEntity<ErrorResponseHandler> hankderIsbnInvalid(IsbnInvalidException ex, WebRequest request){
        ErrorResponseHandler error = new ErrorResponseHandler(
                LocalDateTime.now(),
                400,
                "ISBN invalid",
                ex.getMessage(),
                request.getDescription(false).replace("uri=",""),
                ex.getErrorCode()
        );
        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseHandler> handlerValidation(MethodArgumentNotValidException ex, WebRequest request){

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponseHandler error = new ErrorResponseHandler(
                LocalDateTime.now(),
                400,
                "ISBN invalid",
                message,
                request.getDescription(false).replace("uri=",""),
                "VALIDATION_ERROR"
        );

        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseHandler> handleGenericError(Exception ex, WebRequest request){
        System.err.println("Error " + ex.getMessage());
        ex.printStackTrace();

        ErrorResponseHandler error = new ErrorResponseHandler(
                LocalDateTime.now(),
                500,
                "Internal Server Error",
                "Server Error",
                request.getDescription(false).replace("uri=",""),
                "INTERNAL_ERROR"
        );

        return ResponseEntity.status(500).body(error);
    }

}
