package br.com.igorma.aiextraction.service.impl;

import br.com.igorma.aiextraction.event.EventSpeechToText;
import br.com.igorma.aiextraction.service.SpeechService;
import org.springframework.ai.openai.OpenAiAudioTranscriptionClient;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi.TranscriptResponseFormat;
import org.springframework.ai.openai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.openai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@Service
public class OpenAISpeech implements SpeechService {

    private final OpenAiAudioTranscriptionClient aiClient;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public OpenAISpeech(OpenAiAudioTranscriptionClient aiClient, ApplicationEventPublisher eventPublisher) {
        this.aiClient = aiClient;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public String speechToText(String audioPath) {
        OpenAiAudioTranscriptionOptions transcriptionOptions = OpenAiAudioTranscriptionOptions.builder()
                .withTemperature(0f).withResponseFormat(TranscriptResponseFormat.TEXT).build();

        AudioTranscriptionPrompt transcriptionRequest = new AudioTranscriptionPrompt(
                new FileSystemResource(audioPath), transcriptionOptions);
        AudioTranscriptionResponse response = aiClient.call(transcriptionRequest);

        eventPublisher.publishEvent(new EventSpeechToText(audioPath, response.getResult().getOutput()));
        return response.getResult().getOutput();
    }
}
