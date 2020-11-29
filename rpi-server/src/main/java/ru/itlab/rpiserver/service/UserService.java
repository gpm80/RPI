package ru.itlab.rpiserver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itlab.rpiserver.dao.User;
import ru.itlab.rpiserver.repository.UserRepository;

/**
 * Сервис для работы с пользователями.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Поиск пользователя по имени.
     *
     * @param username имя пользователя
     * @return пользователя
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Поиск пользователя по id.
     *
     * @param id id пользователя
     * @return пользователя
     */
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    /**
     * Сохранить пользователя.
     *
     * @param user пользователь
     * @return сохранённый пользователь
     */
    public User save(User user) {
        return userRepository.save(user);
    }

}
