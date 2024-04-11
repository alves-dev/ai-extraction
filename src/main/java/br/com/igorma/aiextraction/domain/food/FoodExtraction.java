package br.com.igorma.aiextraction.domain.food;

import br.com.igorma.aiextraction.domain.ThemeExtraction;
import br.com.igorma.aiextraction.domain.UUIDService;
import br.com.igorma.aiextraction.domain.event.EventFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class FoodExtraction implements ThemeExtraction {

    private final ApplicationEventPublisher eventPublisher;
    private final UUIDService uuidService;

    private static final String POMPT_STRING = """
            Considere o seguinte contexto,
            quero que você extraia do texto os alimentos que comi e qual a quantidade em gramas de cada um: {text}
                        
            {format}
            """;

    @Autowired
    public FoodExtraction(ApplicationEventPublisher eventPublisher, UUIDService uuidService) {
        this.eventPublisher = eventPublisher;
        this.uuidService = uuidService;
    }

    @Override
    public String getTheme() {
        return "Alimentacao";
    }

    @Override
    public String getPrompt(String text) {
        return POMPT_STRING
                .replace("{text}", text);
    }

    @Override
    public Class<?> getReturnType() {
        return FoodResponseList.class;
    }

    @Override
    public void processResult(Object result) {
        if (result instanceof FoodResponseList response) {
            response.foods().forEach(
                    food -> eventPublisher.publishEvent(
                            new EventFood(uuidService.getPersonId(), food.food(), food.weight()))
            );
        }
    }
}
