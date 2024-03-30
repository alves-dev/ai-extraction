package br.com.igorma.aiextraction.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractionRepository extends MongoRepository<Extraction, String> {
}
