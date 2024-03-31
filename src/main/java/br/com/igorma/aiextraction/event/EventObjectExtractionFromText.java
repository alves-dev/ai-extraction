package br.com.igorma.aiextraction.event;

import org.springframework.ai.chat.metadata.Usage;

public class EventObjectExtractionFromText extends EventLLMBase {
    private final Object request;
    private final Object response;
    private final Usage usage;

    public EventObjectExtractionFromText(Object request, Object response, Usage usage) {
        super();
        this.request = request;
        this.response = response;
        this.usage = usage;
    }

    public Object getRequest() {
        return request;
    }

    public Object getResponse() {
        return response;
    }

    public Usage getUsage() {
        return usage;
    }
}
