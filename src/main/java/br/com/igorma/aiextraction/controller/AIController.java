package br.com.igorma.aiextraction.controller;

import br.com.igorma.aiextraction.service.IntentExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ai")
public class AIController {

    private final IntentExtractionService service;

    @Autowired
    public AIController(IntentExtractionService service) {
        this.service = service;
    }

    @GetMapping("/open")
    public ResponseEntity<Object> extractionIntent(@RequestParam String text) {
        return ResponseEntity.ok(service.speechToText(text));
    }

}
