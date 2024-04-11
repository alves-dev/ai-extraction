package br.com.igorma.aiextraction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class AiExtractionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiExtractionApplication.class, args);
    }

}
