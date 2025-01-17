package ru.itlab.rpiserver.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Предложение.
 */
@Document(indexName = "rpi-innovation")
public class Innovation {
    @Id
    private String id;
    @Field(index = false)
    private String userId;
    private String userName;
    @Field(type = FieldType.Date, format = DateFormat.date_time)
    private Date date;
    @Field(index = false)
    private String description;
    @Field(type = FieldType.Text)
    private String text;
    @Field
    private Boolean anonymous;
    @Field
    private List<String> voteCounter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public List<String> getVoteCounter() {
        return voteCounter;
    }

    public void setVoteCounter(List<String> voteCounter) {
        this.voteCounter = voteCounter;
    }

    @Override
    public String toString() {
        return "Innovation{" +
            "id='" + id + '\'' +
            ", userId='" + userId + '\'' +
            ", userName='" + userName + '\'' +
            ", date=" + date +
            ", description='" + description + '\'' +
            ", text='" + text + '\'' +
            ", anonymous=" + anonymous +
            ", voteCounter=" + voteCounter +
            '}';
    }
}
