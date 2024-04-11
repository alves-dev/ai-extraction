package br.com.igorma.aiextraction.domain.water;

import br.com.igorma.aiextraction.domain.ThemeExtraction;
import br.com.igorma.aiextraction.domain.UUIDService;
import br.com.igorma.aiextraction.domain.event.EventLiquidFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class WaterExtraction implements ThemeExtraction {

    private final ApplicationEventPublisher eventPublisher;
    private final UUIDService uuidService;

    private static final String POMPT_STRING = """
            Extraia do texto a quantidade de água em mililitro: {text}
                        
            {format}
            """;

    @Autowired
    public WaterExtraction(ApplicationEventPublisher eventPublisher, UUIDService uuidService) {
        this.eventPublisher = eventPublisher;
        this.uuidService = uuidService;
    }

    @Override
    public String getTheme() {
        return "Agua";
    }

    @Override
    public String getPrompt(String text) {
        return POMPT_STRING
                .replace("{text}", text);
    }

    @Override
    public Class<?> getReturnType() {
        return WaterResponse.class;
    }

    @Override
    public void processResult(Object result) {
        if (result instanceof WaterResponse response) {
            eventPublisher.publishEvent(
                    EventLiquidFood.ofWater(uuidService.getPersonId(), response.amount()));
        }
    }
}
