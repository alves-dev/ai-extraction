package br.com.igorma.aiextraction.event;

import java.time.LocalDateTime;

public class EventLLMBase {
    private final LocalDateTime dateExtraction;

    public EventLLMBase() {
        this.dateExtraction = LocalDateTime.now();
    }

    public LocalDateTime getDateExtraction() {
        return dateExtraction;
    }
}
