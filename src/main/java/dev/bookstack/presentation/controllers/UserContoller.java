package dev.bookstack.presentation.controllers;

import dev.bookstack.application.dto.request.CreateUserRequestDto;
import dev.bookstack.application.dto.request.UpdateUserRequestDto;
import dev.bookstack.application.dto.response.UserResponseDto;
import dev.bookstack.application.usecases.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookStack/")
@RequiredArgsConstructor
public class UserContoller {

    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserByIdUseCase deleteUserByIdUseCase;
    private final FindAllUsersUseCase findAllUsersUseCase;
    private final FindUserByCpfUseCase findUserByCpfUseCase;
    private final FindUserByEmailUseCase findUserByEmailUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final FindUserByNameUseCase findUserByNameUseCase;
    private final FindUserIsActiveUseCase findUserIsActiveUseCase;
    private final FindUserIsAdminUseCase findUserIsAdminUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @PostMapping("/api/users")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateUserRequestDto request){
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserUseCase.execute(request));
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponseDto>> findAllUsers(){
        return ResponseEntity.ok(findAllUsersUseCase.execute());
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponseDto> findById (@PathVariable Long id){
        return ResponseEntity.ok(findUserByIdUseCase.execute(id));
    }

    @GetMapping("/api/users/search/cpf")
    public ResponseEntity<UserResponseDto> findByCpf (@RequestParam("cpf") String cpf){
        return ResponseEntity.ok(findUserByCpfUseCase.execute(cpf));
    }

    @GetMapping("/api/users/search/email")
    public ResponseEntity<UserResponseDto> findByEmail (@RequestParam("email") String email){
        return ResponseEntity.ok(findUserByEmailUseCase.execute(email));
    }

    @GetMapping("/api/users/search/name")
    public ResponseEntity<List<UserResponseDto>> findByNAME(@RequestParam("name") String name){
        return ResponseEntity.ok(findUserByNameUseCase.execute(name));
    }

    @GetMapping("api/users/isAdmin")
    public ResponseEntity<List<UserResponseDto>> findIsAdmin (){
        return ResponseEntity.ok(findUserIsAdminUseCase.execute());
    }

    @GetMapping("/api/users/isActive")
    public ResponseEntity<List<UserResponseDto>> findIsActive(){
        return ResponseEntity.ok(findUserIsActiveUseCase.execute());
    }

    @PatchMapping("/api/users/{id}")
    public ResponseEntity<UserResponseDto> updatedUser (@PathVariable Long id, UpdateUserRequestDto request){
        return ResponseEntity.ok(updateUserUseCase.execute(id, request));
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id){
        deleteUserByIdUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
