package ru.itlab.rpiserver.repository;

import java.util.stream.Stream;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.itlab.rpiserver.dao.Commentary;

/**
 * Репозиторий для работы с комментариями.
 */
public interface CommentaryRepository extends ElasticsearchRepository<Commentary, String> {
    Stream<Commentary> findByInnovationId(String id);
}
