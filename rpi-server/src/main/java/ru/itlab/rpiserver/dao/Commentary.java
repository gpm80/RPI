package ru.itlab.rpiserver.dao;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Комментарий к предложению.
 */
@Document(indexName = "rpi-commentary")
public class Commentary {
    @Id
    private String id;
    @Field(index = false)
    private String innovationId;
    @Field(index = false)
    private String message;
    @Field(type = FieldType.Date, format = DateFormat.date_time)
    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInnovationId() {
        return innovationId;
    }

    public void setInnovationId(String innovationId) {
        this.innovationId = innovationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commentary{" +
            "id='" + id + '\'' +
            ", innovationId='" + innovationId + '\'' +
            ", message='" + message + '\'' +
            ", date=" + date +
            '}';
    }
}
