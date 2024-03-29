package br.com.igorma.aiextraction.database;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Extraction {

    // TODO: melhorar isso aqui, salvar um json bonito

    @Id
    private String id;
    private LocalDateTime dateExtraction;
    private Object request;
    private Object response;
    private Object usage;

    public Extraction(Object request, Object response, Object usage) {
        this.dateExtraction = LocalDateTime.now();
        this.request = request;
        this.response = response;
        this.usage = usage;
    }
}
