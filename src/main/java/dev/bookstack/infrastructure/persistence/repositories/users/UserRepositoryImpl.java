package dev.bookstack.infrastructure.persistence.repositories.users;

import dev.bookstack.domain.entities.Users;
import dev.bookstack.domain.repositories.UsersRepository;
import dev.bookstack.infrastructure.persistence.mappers.UsersEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UsersRepository {

    private final UserJpaRepository repository;

    public UserRepositoryImpl(UserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Users save(Users user) {
        return UsersEntityMapper.toDomain(repository.save(UsersEntityMapper.toEntity(user)));
    }

    @Override
    public Optional<Users> findById(Long id) {
        return repository.findById(id).map(UsersEntityMapper::toDomain);
    }

    @Override
    public List<Users> findAll() {
        return repository.findAll().stream().map(UsersEntityMapper::toDomain).toList();
    }

    @Override
    public List<Users> findByName(String name) {
        return repository.findByNameContainingIgnoreCase(name).stream().map(UsersEntityMapper::toDomain).toList();
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return repository.findByEmailContainingIgnoreCase(email).map(UsersEntityMapper::toDomain);
    }

    @Override
    public Optional<Users> findByCpf(String cpf) {
        return repository.findByCpf(cpf).map(UsersEntityMapper::toDomain);
    }

    @Override
    public List<Users> findByIsAdminTrue() {
        return repository.findByIsAdminTrue().stream().map(UsersEntityMapper::toDomain).toList();
    }

    @Override
    public List<Users> findByIsActiveTrue() {
        return repository.findByIsActiveTrue().stream().map(UsersEntityMapper::toDomain).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }
}
