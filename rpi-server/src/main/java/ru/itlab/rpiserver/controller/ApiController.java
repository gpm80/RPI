package ru.itlab.rpiserver.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.itlab.rpiserver.dao.Commentary;
import ru.itlab.rpiserver.dao.Innovation;
import ru.itlab.rpiserver.dao.User;
import ru.itlab.rpiserver.service.InnovationService;
import ru.itlab.rpiserver.service.UserService;

/**
 * API.
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UserService userService;
    @Autowired
    private InnovationService innovationService;

    /**
     * Аутентификация.
     *
     * @param username имя пользователя
     * @param password пароль
     * @return id пользователя
     */
    @GetMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestParam String username, @RequestParam(required = false) String password) {
        final User user = userService.findByUsername(username).orElseGet(() -> {
            final User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(username);
            return userService.save(newUser);
        });
        return user.getId();
    }

    /**
     * Создать предложение.
     *
     * @param innovation предложение
     */
    @PostMapping(path = "/postInnovation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Innovation postInnovation(@RequestBody Innovation innovation) {
        innovation.setDate(new Date(System.currentTimeMillis()));
        return innovationService.saveInnovation(innovation);
    }

    /**
     * Создать комментарий к предложению
     *
     * @param commentary комментарий
     */
    @PostMapping(path = "/postComment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Commentary postCommentary(@RequestBody Commentary commentary) {
        commentary.setDate(new Date(System.currentTimeMillis()));
        return innovationService.saveCommentary(commentary);
    }

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Boolean upload(@RequestParam("files") MultipartFile[] files) throws IOException {
        //TODO files
        return true;
    }

    /**
     * Получение списка всех предложений.
     *
     * @return список предложений
     */
    @GetMapping(path = "/getAllInnovations", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Innovation> getAll() {
        return innovationService.getAllInnovations();
    }

    /**
     * Получение комментариев к предложению.
     *
     * @param id id предложения
     * @return список комментариев
     */
    @GetMapping(path = "/getCommentaries", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Commentary> getCommentaries(@RequestParam String id) {
        return innovationService.getCommentariesByInnovation(id);
    }
}
