package ru.itlab.rpiserver.dao;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.web.multipart.MultipartFile;

/**
 * Файлы
 */
@Document(indexName = "rpi-innovation-files")
public class InnovationFiles {
    @Id
    private String id;
    @Field(index = false)
    private String innovationId;
    @Field(index = false)
    private List<MultipartFile> files;

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

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
