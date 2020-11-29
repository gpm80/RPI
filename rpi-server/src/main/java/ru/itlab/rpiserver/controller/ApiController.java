package ru.itlab.rpiserver.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.itlab.rpiserver.dao.Commentary;
import ru.itlab.rpiserver.dao.Innovation;
import ru.itlab.rpiserver.dao.InnovationFile;
import ru.itlab.rpiserver.dao.User;
import ru.itlab.rpiserver.service.InnovationService;
import ru.itlab.rpiserver.service.UserService;

/**
 * API.
 */
@RestController
@RequestMapping("/api")
public class ApiController {


    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

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
    public User login(@RequestParam String username, @RequestParam(required = false) String password) {
        return userService.findByUsername(username).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.UNAUTHORIZED, "User not found"));
    }

    /**
     * Регистрация в системе.
     *
     * @param user пользователь
     * @return зарегистрированный пользователь
     */
    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User register(@RequestBody User user) {
        return userService.save(user);
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

    /**
     * Загрузить файлы по предложению.
     *
     * @param files полученные файлы
     * @param id    id предложения
     * @return true
     */
    @PostMapping(value = "/upload/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Boolean upload(@RequestParam MultipartFile[] file, @PathVariable("id") String id) {
        final List<InnovationFile> iFiles = Arrays.stream(file).map(multipartFile -> {
            try {
                final InnovationFile innovationFile = new InnovationFile();
                innovationFile.setInnovationId(id);
                innovationFile.setContentType(multipartFile.getContentType());
                innovationFile.setFile(multipartFile.getBytes());
                innovationFile.setFileName(multipartFile.getOriginalFilename());
                return innovationFile;
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        innovationService.saveFiles(iFiles);
        return true;
    }

    /**
     * Голос за предложение
     *
     * @param innovationId id предложения
     * @param userId       id пользователя
     * @return true
     */
    @PostMapping(path = "/vote", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean vote(@RequestParam String innovationId, @RequestParam String userId) {
        innovationService.voteInnovation(innovationId, userId);
        return true;
    }

    /**
     * Получить файлы по предложению
     * @param id предложения
     * @return список файлов
     */
    @GetMapping(path = "/getFiles", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<MultipartFile> getFiles(@RequestParam String id) {
        return innovationService.getFiles(id).stream().map(innovationFile -> {
            try {
                if (innovationFile.getContentType() == null) {
                    return null;
                }
                final String[] split = innovationFile.getFileName().contains(".")
                    ? innovationFile.getFileName().split("\\.")
                    : new String[] {innovationFile.getFileName()};
                final File tempFile = Files.createTempFile(split[0], split.length == 2 ? split[1] : null).toFile();
                FileUtils.writeByteArrayToFile(tempFile, innovationFile.getFile());
                final DiskFileItem item = new DiskFileItem("file", innovationFile.getContentType(),
                    true, innovationFile.getFileName(),
                    (int) tempFile.length(), tempFile);
                item.getOutputStream();
                return new CommonsMultipartFile(item);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
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
