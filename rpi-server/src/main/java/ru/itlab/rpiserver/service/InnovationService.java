package ru.itlab.rpiserver.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itlab.rpiserver.dao.Commentary;
import ru.itlab.rpiserver.dao.Innovation;
import ru.itlab.rpiserver.repository.CommentaryRepository;
import ru.itlab.rpiserver.repository.InnovationFilesRepository;
import ru.itlab.rpiserver.repository.InnovationRepository;

/**
 * Сервис для работы с предложениями.
 */
@Service
public class InnovationService {

    @Autowired
    private InnovationRepository innovationRepository;
    @Autowired
    private InnovationFilesRepository innovationFilesRepository;
    @Autowired
    private CommentaryRepository commentaryRepository;
    @Autowired
    private UserService userService;

    /**
     * Получить все предложения.
     *
     * @return список предложений
     */
    public List<Innovation> getAllInnovations() {
        return StreamSupport.stream(innovationRepository.findAll().spliterator(), false)
            .peek(innovation -> {
                if (!innovation.isAnonymous()) {
                    userService.findById(innovation.getUserId()).ifPresent(user -> innovation.setUserName(user.getUsername()));
                }
            })
            .collect(Collectors.toList());
    }

    /**
     * Поиск всех комментариев к предложению.
     *
     * @param id id предложения
     * @return список комментариев
     */
    public List<Commentary> getCommentariesByInnovation(String id) {
        return StreamSupport.stream(commentaryRepository.findByInnovationId(id).spliterator(), false)
            .collect(Collectors.toList());
    }

    /**
     * Сохранить предложение.
     *
     * @param innovation предложение
     * @return предложение
     */
    public Innovation saveInnovation(Innovation innovation) {
        final Innovation save = innovationRepository.save(innovation);
        if (!innovation.isAnonymous()) {
            userService.findById(save.getUserId()).ifPresent(user -> save.setUserName(user.getUsername()));
        }
        return save;
    }

    /**
     * Сохранить комментарий.
     *
     * @param commentary комментарий
     * @return комментарий
     */
    public Commentary saveCommentary(Commentary commentary) {
        return commentaryRepository.save(commentary);
    }
}
