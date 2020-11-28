package ru.itlab.rpiserver.dao;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * Пользователь.
 */
@Document(indexName = "rpi-users")
public class User {

    @Id
    private String id;
    @Field(index = false)
    private String username;
    @Field(index = false)
    private String password;

    public static User of(Map<String,String> parameters) {
        final User user = new User();
        user.setUsername(parameters.get("username"));
        user.setUsername(parameters.get("password"));
        return user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}