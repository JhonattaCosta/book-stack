package dev.bookstack.domain.repositories;

import dev.bookstack.domain.entities.Users;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {

    Users save (Users user);

    Optional<Users> findById(Long id);

    List<Users> findAll();

    List<Users> findByName(String name);

    List<Users> findByEmail(String email);

    List<Users> findByCpf(String cpf);

    List<Users> findByIsAdminTrue();

    List<Users> findByIsActiveTrue();

    void delete (Long id);

    boolean existByEmail(String email);

    boolean existByCpf(String cpf);

}
