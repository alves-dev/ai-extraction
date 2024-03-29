package br.com.igorma.aiextraction.service.impl;

import br.com.igorma.aiextraction.database.Extraction;
import br.com.igorma.aiextraction.database.ExtractionRepository;
import br.com.igorma.aiextraction.model.Intent;
import br.com.igorma.aiextraction.model.IntentResponseList;
import br.com.igorma.aiextraction.service.IntentExtractionService;
import java.util.List;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenAIIntentExtraction implements IntentExtractionService {

    private final OpenAiChatClient aiClient;
    private final ExtractionRepository repository;

    @Autowired
    public OpenAIIntentExtraction(OpenAiChatClient aiClient, ExtractionRepository repository) {
        this.aiClient = aiClient;
        this.repository = repository;
    }

    @Override
    public IntentResponseList intentExtractionToText(String text) {
        if (text == null || text.length() < 10) {
            return new IntentResponseList(List.of());
        }

        BeanOutputParser<IntentResponseList> outputParser = new BeanOutputParser<>(
            IntentResponseList.class);

        String promptString = """
            Considere o seguinte contexto,
            quero que você extraia do texto: {text}
            as seguintes intenções, cada uma tem um tipo para o qual o valor deve ser convertido: {intents}
            apenas caso encontre elas no texto.
            
            {format}
            """;

        PromptTemplate promptTemplate = new PromptTemplate(promptString);
        promptTemplate.add("intents", Intent.listIntentsAndTypesInPortuguese());
        promptTemplate.add("text", text);
        promptTemplate.add("format", outputParser.getFormat());

        promptTemplate.setOutputParser(outputParser);

        Prompt prompt = promptTemplate.create();
        ChatResponse response = aiClient.call(prompt);
        IntentResponseList result = outputParser.parse(response.getResult().getOutput().getContent());

        repository.save(new Extraction(prompt.getContents(), result, response.getMetadata().getUsage()));
        return result;
    }
}
