package dev.bookstack.infrastructure.persistence.repositories.users;

import dev.bookstack.infrastructure.persistence.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UsersEntity, Long> {

    Optional<UsersEntity> findByEmailContainingIgnoreCase(String email);
    Optional<UsersEntity> findByCpf(String cpf);
    List<UsersEntity> findByNameContainingIgnoreCase(String name);
    List<UsersEntity> findByIsAdminTrue();
    List<UsersEntity> findByIsActiveTrue();
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);

}
