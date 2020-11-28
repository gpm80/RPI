package ru.itlab.rpiserver.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.itlab.rpiserver.dao.InnovationFiles;

public interface InnovationFilesRepository extends ElasticsearchRepository<InnovationFiles, String> {
    Optional<InnovationFiles> findByInnovationId(String id);
}
