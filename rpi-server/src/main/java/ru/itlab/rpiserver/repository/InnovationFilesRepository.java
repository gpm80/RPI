package ru.itlab.rpiserver.repository;

import java.util.stream.Stream;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.itlab.rpiserver.dao.InnovationFile;

public interface InnovationFilesRepository extends ElasticsearchRepository<InnovationFile, String> {
    Stream<InnovationFile> findByInnovationId(String id);
    void deleteByInnovationId(String id);
}
