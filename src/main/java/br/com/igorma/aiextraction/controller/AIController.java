package br.com.igorma.aiextraction.controller;

import br.com.igorma.aiextraction.infrastructure.ExtractionRepository;
import br.com.igorma.aiextraction.infrastructure.SpeechToTextRepository;
import br.com.igorma.aiextraction.service.AIService;
import br.com.igorma.aiextraction.service.ObjectExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("ai")
public class AIController {

    private final ObjectExtractionService service;
    private final AIService aiService;
    private final ExtractionRepository extractionRepository;
    private final SpeechToTextRepository speechToTextRepository;

    @Autowired
    public AIController(ObjectExtractionService service, AIService aiService, ExtractionRepository extractionRepository,
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
    public ResponseEntity<Object> extractionObjectGetForNavigator(@RequestParam String text, @RequestParam String theme) {
        return ResponseEntity.ok(service.objectExtractionFromText(text, theme));
    }

    @PostMapping("/extract-from-text")
    public ResponseEntity<Object> extractionObject(@RequestParam String text, @RequestParam String themeSelected) {
        return ResponseEntity.ok(service.objectExtractionFromText(text, themeSelected));
    }

    @PostMapping("/upload-audio")
    public ResponseEntity<Object> uploadAudio(
            @RequestParam("audioFile") MultipartFile audioFile,
            @RequestParam("themeSelected") String themeSelected
    ) {
        String path = aiService.saveMultipartFile(audioFile);
        Object result = aiService.extractFromAudio(path, themeSelected);
        return ResponseEntity.ok(result);
    }
}
