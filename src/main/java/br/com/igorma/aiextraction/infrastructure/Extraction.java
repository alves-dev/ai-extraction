package br.com.igorma.aiextraction.infrastructure;

import br.com.igorma.aiextraction.event.EventObjectExtractionFromText;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "extraction")
public class Extraction {

    @Id
    private String id;

    @Field("date_extraction")
    private LocalDateTime dateExtraction;

    private Object request;
    private Object response;
    private MyUsage usage;

    public Extraction(EventObjectExtractionFromText event) {
        this.dateExtraction = event.getDateExtraction();
        this.request = event.getRequest();
        this.response = event.getResponse();
        this.usage = new MyUsage(event.getUsage());
    }

    public Extraction() {
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDateExtraction() {
        return dateExtraction;
    }

    public Object getRequest() {
        return request;
    }

    public Object getResponse() {
        return response;
    }

    public MyUsage getUsage() {
        return usage;
    }
}
