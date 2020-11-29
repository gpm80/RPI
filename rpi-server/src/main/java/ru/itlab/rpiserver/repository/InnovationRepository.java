package ru.itlab.rpiserver.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.itlab.rpiserver.dao.Innovation;

/**
 * Репозиторий для работы с предложениями.
 */
public interface InnovationRepository extends ElasticsearchRepository<Innovation, String> {
}
