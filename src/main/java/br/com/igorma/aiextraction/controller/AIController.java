package br.com.igorma.aiextraction.controller;

import br.com.igorma.aiextraction.infrastructure.ExtractionRepository;
import br.com.igorma.aiextraction.infrastructure.SpeechToTextRepository;
import br.com.igorma.aiextraction.model.IntentResponseList;
import br.com.igorma.aiextraction.model.IntentType;
import br.com.igorma.aiextraction.service.AIService;
import br.com.igorma.aiextraction.service.IntentExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("ai")
public class AIController {

    private final IntentExtractionService service;
    private final AIService aiService;
    private final ExtractionRepository extractionRepository;
    private final SpeechToTextRepository speechToTextRepository;

    @Autowired
    public AIController(IntentExtractionService service, AIService aiService, ExtractionRepository extractionRepository,
                        SpeechToTextRepository speechToTextRepository) {
        this.service = service;
        this.aiService = aiService;
        this.extractionRepository = extractionRepository;
        this.speechToTextRepository = speechToTextRepository;
    }

    @GetMapping("/list/extractions")
    public ResponseEntity<Object> extractions() {
        List<Object> list = List.of(
                extractionRepository.findAll(),
                speechToTextRepository.findAll()
        );
        return ResponseEntity.ok(list);
    }

    @GetMapping("/extract-from-text")
    public ResponseEntity<IntentResponseList> extractionIntent(@RequestParam String text) {
        return ResponseEntity.ok(service.intentExtractionToText(text));
    }

    @PostMapping("/upload-audio")
    public ResponseEntity<IntentResponseList> uploadAudio(
            @RequestParam("audioFile") MultipartFile audioFile,
            @RequestParam("themeSelected") String themeSelected
    ) {
        String path = aiService.saveMultipartFile(audioFile);
        IntentResponseList result = aiService.extractFromAudio(path, IntentType.valuePt(themeSelected));
        return ResponseEntity.ok(result);
    }
}
