package dev.bookstack.infrastructure.persistence.repositories.users;


import dev.bookstack.domain.entities.Users;
import dev.bookstack.domain.entities.valueObjects.Cpf;
import dev.bookstack.domain.entities.valueObjects.Email;
import dev.bookstack.infrastructure.persistence.entities.UsersEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsersRepositoryImplTest {

    @Mock
    private UserJpaRepository repositoryJpa;

    @InjectMocks
    private UserRepositoryImpl userRepository;

    @Test
    void shouldSaveUser_Successfully() {

        Users userDomain = new Users(1L, "Jhonatta", new Email("test@test.com"), new Cpf("54647142949"), false, true, LocalDateTime.now(), LocalDateTime.now());
        UsersEntity savedUser = new UsersEntity(1L, "Jhonatta", "test@test.com", "54647142949", false, true, LocalDateTime.now(), LocalDateTime.now());

        when(repositoryJpa.save(any(UsersEntity.class))).thenReturn(savedUser);

        Users result = userRepository.save(userDomain);

        verify(repositoryJpa).save(any(UsersEntity.class));
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(userDomain.getName());

    }

    @Test
    void shouldFindUserById() {

        Optional<Users> userDomain = Optional.of(new Users(1L, "Jhonatta", new Email("test@test.com"), new Cpf("54647142949"), false, true, LocalDateTime.now(), LocalDateTime.now()));
        Optional<UsersEntity> userEntity = Optional.of(new UsersEntity(1L, "Jhonatta", "test@test.com", "54647142949", false, true, LocalDateTime.now(), LocalDateTime.now()));


        when(repositoryJpa.findById(1l)).thenReturn(userEntity);

        Optional<Users> result = userRepository.findById(1L);

        assertThat(result.isPresent());
        assertThat(result.get().getId()).isEqualTo(userDomain.get().getId());


    }

    @Test
    void shouldShowAllUsers() {
        List<Users> listDomain = List.of(
                new Users(1L, "Jhonatta", new Email("test@test.com"), new Cpf("54647142949"), false, true, LocalDateTime.now(), LocalDateTime.now()),
                new Users(2L, "Lidiane", new Email("test2@test.com"), new Cpf("48366915328"), false, true, LocalDateTime.now(), LocalDateTime.now())
        );
        List<UsersEntity> listEntity = List.of(
                new UsersEntity(1L, "Jhonatta", "test@test.com", "54647142949", false, true, LocalDateTime.now(), LocalDateTime.now()),
                new UsersEntity(2L, "Lidiane", "test2@test.com", "48366915328", false, true, LocalDateTime.now(), LocalDateTime.now())
        );

        when(repositoryJpa.findAll()).thenReturn(listEntity);

        List<Users> result = userRepository.findAll();

        assertThat(result.size()).isEqualTo(listDomain.size());
        assertThat(result.get(0).getName()).isEqualTo(listDomain.get(0).getName());

    }

    @Test
    void shouldShowAllUsers_FromSelectedName(){
        List<Users> listDomain = List.of(
                new Users(1L, "Jhonatta", new Email("test@test.com"), new Cpf("54647142949"), false, true, LocalDateTime.now(), LocalDateTime.now()),
                new Users(2L, "Jhonatan", new Email("test2@test.com"), new Cpf("48366915328"), false, true, LocalDateTime.now(), LocalDateTime.now())
        );
        List<UsersEntity> listEntity = List.of(
                new UsersEntity(1L, "Jhonatta", "test@test.com", "54647142949", false, true, LocalDateTime.now(), LocalDateTime.now()),
                new UsersEntity(2L, "Jhonatan", "test2@test.com", "48366915328", false, true, LocalDateTime.now(), LocalDateTime.now())
        );

        when(repositoryJpa.findByNameContainingIgnoreCase("Jhona")).thenReturn(listEntity);

        List<Users> result = userRepository.findByName("Jhona");


        assertThat(result.size()).isEqualTo(listDomain.size());
        assertThat(result.get(0).getName()).isEqualTo(listDomain.get(0).getName());
    }

    @Test
    void shouldShowUserByEmail(){

        Optional<Users> userDomain = Optional.of(new Users(1L, "Jhonatta", new Email("test@test.com"), new Cpf("54647142949"), false, true, LocalDateTime.now(), LocalDateTime.now()));
        Optional<UsersEntity> userEntity = Optional.of(new UsersEntity(1L, "Jhonatta", "test@test.com", "54647142949", false, true, LocalDateTime.now(), LocalDateTime.now()));

        when(repositoryJpa.findByEmailContainingIgnoreCase("test@test.com")).thenReturn(userEntity);

        Optional<Users> result = userRepository.findByEmail("test@test.com");

        assertThat(result.get().getEmail().getValue()).isEqualTo("test@test.com");
        assertThat(result.get().getEmail().getValue()).isEqualTo(userDomain.get().getEmail().getValue());

    }

    @Test
    void shouldShowUserByCpf(){

        Optional<Users> userDomain = Optional.of(new Users(1L, "Jhonatta", new Email("test@test.com"), new Cpf("54647142949"), false, true, LocalDateTime.now(), LocalDateTime.now()));
        Optional<UsersEntity> userEntity = Optional.of(new UsersEntity(1L, "Jhonatta", "test@test.com", "54647142949", false, true, LocalDateTime.now(), LocalDateTime.now()));

        when(repositoryJpa.findByCpf("54647142949")).thenReturn(userEntity);

        Optional<Users> result = userRepository.findByCpf("54647142949");

        assertThat(result.get().getCpf().getValue()).isEqualTo("54647142949");
        assertThat(result.get().getCpf().getValue()).isEqualTo(userDomain.get().getCpf().getValue());

    }

    @Test
    void shouldShowAllUsersIsAdminTrue(){
        List<Users> listDomain = List.of(
                new Users(1L, "Jhonatta", new Email("test@test.com"), new Cpf("54647142949"), true, true, LocalDateTime.now(), LocalDateTime.now()),
                new Users(2L, "Lidiane", new Email("test2@test.com"), new Cpf("48366915328"), true, true, LocalDateTime.now(), LocalDateTime.now())
        );
        List<UsersEntity> listEntity = List.of(
                new UsersEntity(1L, "Jhonatta", "test@test.com", "54647142949", true, true, LocalDateTime.now(), LocalDateTime.now()),
                new UsersEntity(2L, "Lidiane", "test2@test.com", "48366915328", true, true, LocalDateTime.now(), LocalDateTime.now())
        );

        when(repositoryJpa.findByIsAdminTrue()).thenReturn(listEntity);

        List<Users> result = userRepository.findByIsAdminTrue();

        assertThat(result.size()).isEqualTo(listDomain.size());
        assertThat(result.get(0).isAdmin()).isTrue();
        assertThat(result.get(0).getName()).isEqualTo(listDomain.get(0).getName());

    }

    @Test
    void shouldShowAllUsersIsActiveTrue(){
        List<Users> listDomain = List.of(
                new Users(1L, "Jhonatta", new Email("test@test.com"), new Cpf("54647142949"), false, true, LocalDateTime.now(), LocalDateTime.now()),
                new Users(2L, "Lidiane", new Email("test2@test.com"), new Cpf("48366915328"), false, true, LocalDateTime.now(), LocalDateTime.now())
        );
        List<UsersEntity> listEntity = List.of(
                new UsersEntity(1L, "Jhonatta", "test@test.com", "54647142949", false, true, LocalDateTime.now(), LocalDateTime.now()),
                new UsersEntity(2L, "Lidiane", "test2@test.com", "48366915328", false, true, LocalDateTime.now(), LocalDateTime.now())
        );

        when(repositoryJpa.findByIsActiveTrue()).thenReturn(listEntity);

        List<Users> result = userRepository.findByIsActiveTrue();

        assertThat(result.size()).isEqualTo(listDomain.size());
        assertThat(result.get(0).isActive()).isTrue();
        assertThat(result.get(0).getName()).isEqualTo(listDomain.get(0).getName());

    }

    @Test
    void shouldDeleteUserById(){
        Long userId = 1L;

        userRepository.delete(userId);

        verify(repositoryJpa).deleteById(1L);
    }

    @Test
    void shouldReturnTrue_whenEmailExist(){
        when(repositoryJpa.existsByEmail("test@test.com")).thenReturn(true);

        boolean result = userRepository.existsByEmail("test@test.com");

        verify(repositoryJpa).existsByEmail("test@test.com");

        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnTrue_whenEmailCpf(){
        when(repositoryJpa.existsByCpf("54647142949")).thenReturn(true);

        boolean result = userRepository.existsByCpf("54647142949");

        verify(repositoryJpa).existsByCpf("54647142949");

        assertThat(result).isTrue();
    }

}
