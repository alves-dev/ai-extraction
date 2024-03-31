package br.com.igorma.aiextraction.domain.water;

import br.com.igorma.aiextraction.domain.ThemeExtraction;
import org.springframework.stereotype.Component;

@Component
public class WaterExtraction implements ThemeExtraction {

    private static final String POMPT_STRING = """
            Extraia do texto a quantidade de água em mililitro: {text}
                        
            {format}
            """;

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
        // TODO: Implementar o processamento do resultado.
        if (result instanceof WaterResponse response) {
            System.out.println(response.amount());
        }
    }
}
