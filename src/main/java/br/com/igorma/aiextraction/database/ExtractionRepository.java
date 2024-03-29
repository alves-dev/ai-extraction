package br.com.igorma.aiextraction.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractionRepository extends MongoRepository<Extraction, String> {
}
