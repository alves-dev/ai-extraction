package br.com.igorma.aiextraction.infrastructure;

import br.com.igorma.aiextraction.event.EventSpeechToText;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "speech_to_text")
public class SpeechToText {

    @Id
    private String id;

    @Field("date_extraction")
    private LocalDateTime dateExtraction;

    private String filePath;
    private String text;

    public SpeechToText(EventSpeechToText event) {
        this.dateExtraction = event.getDateExtraction();
        this.filePath = event.getFilePath();
        this.text = event.getText();
    }

    public SpeechToText() {
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDateExtraction() {
        return dateExtraction;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getText() {
        return text;
    }
}
