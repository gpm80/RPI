package ru.itlab.rpiserver.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.itlab.rpiserver.dao.User;

/**
 * Репозиторий для работы с пользователями.
 */
public interface UserRepository extends ElasticsearchRepository<User, String> {
    Optional<User> findByUsername(String username);
}
