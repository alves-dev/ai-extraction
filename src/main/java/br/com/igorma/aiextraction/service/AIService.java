package br.com.igorma.aiextraction.service;

import br.com.igorma.aiextraction.model.IntentResponseList;
import br.com.igorma.aiextraction.model.IntentType;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class AIService {

    @Value("${audio.upload.dir}")
    private String uploadDir;

    private final IntentExtractionService intentExtractionService;
    private final SpeechService speechService;

    public AIService(IntentExtractionService service, SpeechService speechService) {
        this.intentExtractionService = service;
        this.speechService = speechService;
    }

    public IntentResponseList extractFromAudio(String filePath, IntentType intentType) {
        if (intentType == null) {
            throw new IllegalArgumentException("Intent type is required");
        }
        String text = speechService.speechToText(filePath);
        return intentExtractionService.intentExtractionToText(text, intentType);
    }

    public String saveMultipartFile(MultipartFile file) {
        try {
            String suffix = LocalDateTime.now()
                    .toString()
                    .replace("T", "_")
                    .replace(":", "-")
                    .split("\\.")[0];
            String fileName = uploadDir + File.separator + suffix + ".webm";
            FileOutputStream outputStream = new FileOutputStream(fileName);
            outputStream.write(file.getBytes());
            outputStream.close();
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Error saving file", e);
        }
    }

    @PostConstruct
    private void createDirectory() {
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}
