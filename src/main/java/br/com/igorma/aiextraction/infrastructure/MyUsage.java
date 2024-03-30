package br.com.igorma.aiextraction.infrastructure;

import org.springframework.ai.chat.metadata.Usage;

public record MyUsage(Long promptTokens, Long completionTokens, Long totalTokens) {
    public MyUsage(Usage usage) {
        this(usage.getPromptTokens(),
                usage.getGenerationTokens(),
                usage.getTotalTokens()
        );
    }
}
