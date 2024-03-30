package br.com.igorma.aiextraction.infrastructure;

import br.com.igorma.aiextraction.event.EventIntentExtraction;
import br.com.igorma.aiextraction.event.EventSpeechToText;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    private final ExtractionRepository extractionRepository;
    private final SpeechToTextRepository speechToTextRepository;

    public DBService( ExtractionRepository extractionRepository, SpeechToTextRepository speechToTextRepository) {
        this.extractionRepository = extractionRepository;
        this.speechToTextRepository = speechToTextRepository;
    }

    @Async
    @EventListener
    public void processEventIntentExtraction(EventIntentExtraction event){
        extractionRepository.save(new Extraction(event));
    }

    @Async
    @EventListener
    public void processEventSpeechToText(EventSpeechToText event){
        speechToTextRepository.save(new SpeechToText(event));
    }
}
