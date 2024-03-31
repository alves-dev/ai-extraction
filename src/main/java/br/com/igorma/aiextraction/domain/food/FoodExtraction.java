package br.com.igorma.aiextraction.domain.food;

import br.com.igorma.aiextraction.domain.ThemeExtraction;
import org.springframework.stereotype.Component;

@Component
public class FoodExtraction implements ThemeExtraction {

    private static final String POMPT_STRING = """
            Considere o seguinte contexto,
            quero que você extraia do texto os alimentos que comi e qual a quantidade em gramas de cada um: {text}
                        
            {format}
            """;

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
        if(result instanceof FoodResponseList){
            FoodResponseList intentBody = (FoodResponseList) result;
            // TODO: Implementar o processamento do resultado.
        }
    }
}
