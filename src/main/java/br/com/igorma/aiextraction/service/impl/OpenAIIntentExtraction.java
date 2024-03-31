package br.com.igorma.aiextraction.service.impl;

import br.com.igorma.aiextraction.domain.ThemeExtraction;
import br.com.igorma.aiextraction.domain.ThemeExtractionProcessor;
import br.com.igorma.aiextraction.event.EventObjectExtractionFromText;
import br.com.igorma.aiextraction.service.ObjectExtractionService;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OpenAIIntentExtraction implements ObjectExtractionService {

    private final OpenAiChatClient aiClient;
    private final ThemeExtractionProcessor themeExtractionProcessor;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public OpenAIIntentExtraction(OpenAiChatClient aiClient, ThemeExtractionProcessor themeExtractionProcessor,
                                  ApplicationEventPublisher eventPublisher) {
        this.aiClient = aiClient;
        this.themeExtractionProcessor = themeExtractionProcessor;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Object objectExtractionFromText(String text, String theme) {
        if (!inputIsValid(text)) {
            return null;
        }

        ThemeExtraction themeExtraction = themeExtractionProcessor.getThemeExtraction(theme);

        Class<?> type = themeExtraction.getReturnType();

        BeanOutputParser<?> outputParser = new BeanOutputParser<>(type);

        PromptTemplate promptTemplate = new PromptTemplate(themeExtraction.getPrompt(text));
        promptTemplate.add("format", outputParser.getFormat());
        promptTemplate.setOutputParser(outputParser);

        Prompt prompt = promptTemplate.create();

        ChatResponse response = aiClient.call(prompt);
        Object result = outputParser.parse(response.getResult().getOutput().getContent());

        themeExtraction.processResult(result);
        eventPublisher.publishEvent(new EventObjectExtractionFromText(prompt.getContents(), result, response.getMetadata().getUsage()));
        return result;
    }

    private boolean inputIsValid(String text) {
        return text != null && text.length() >= 10;
    }
}
