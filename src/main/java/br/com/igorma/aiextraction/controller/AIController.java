package br.com.igorma.aiextraction.controller;

import br.com.igorma.aiextraction.service.IntentExtractionService;
import br.com.igorma.aiextraction.service.SpeechService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("ai")
public class AIController {

    @Value("${audio.upload.dir}")
    private String uploadDir;

    private final IntentExtractionService service;
    private final SpeechService speechService;

    @Autowired
    public AIController(IntentExtractionService service, SpeechService speechService) {
        this.service = service;
        this.speechService = speechService;
    }

    @GetMapping("/open")
    public ResponseEntity<Object> extractionIntent(@RequestParam String text) {
        return ResponseEntity.ok(service.intentExtractionToText(text));
    }

    @PostMapping("/upload-audio")
    public ResponseEntity<String> uploadAudio(
        @RequestParam("audioFile") MultipartFile audioFile,
        @RequestParam("themeSelected") String themeSelected
    ) {

        System.out.println("Theme selected: " + themeSelected);

        try {
            // Verifica se a pasta de destino existe, se não, cria
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Salvar o arquivo de áudio no servidor
            String suffix = LocalDateTime.now().toString().replace("T", "_").replace(":", "-")
                .split("\\.")[0];
            String fileName = uploadDir + File.separator + suffix + ".webm";
            FileOutputStream outputStream = new FileOutputStream(fileName);
            outputStream.write(audioFile.getBytes());
            outputStream.close();

            var text = speechService.speechToText(fileName);
            var result = service.intentExtractionToText(text);
            System.out.println(result);

            // Retorna uma resposta de sucesso
            return ResponseEntity.ok("Áudio recebido e salvo com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            // Retorna uma resposta de erro
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao salvar o áudio.");
        }
    }
}
