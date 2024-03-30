package br.com.igorma.aiextraction.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeechToTextRepository extends MongoRepository<SpeechToText, String> {
}
