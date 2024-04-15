package br.com.igorma.aiextraction.domain.body;

import br.com.igorma.aiextraction.domain.ThemeExtraction;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class BodyExtraction implements ThemeExtraction {

    private static final String POMPT_STRING = """
            Considere o seguinte contexto,
            quero que você extraia do texto: {text}
            as seguintes intenções: {intents}
            apenas caso encontre elas no texto.
                        
            {format}
            """;

    private static final List<String> INTENT = List.of(
            "altura",
            "peso",
            // Composição
            "massa_magra", // %
            "massa_gorda", // %
            // Membros superiores
            "braco_relaxado_esquerdo",
            "braco_relaxado_direito",
            "braco_contraido_esquerdo",
            "braco_contraido_direito",
            "antebraco_esquerdo",
            "antebraco_direito",
            // Membros inferiores
            "coxa_esquerda",
            "coxa_direita",
            "panturrilha_esquerda",
            "panturrilha_direita",
            // Tronco
            "torax_relaxado",
            "torax_inspirado",
            "cintura",
            "abdome",
            "quadril",
            // Outros
            "pescoco",
            "ombro"
    );

    @Override
    public String getTheme() {
        return "Medidas Corporais";
    }

    @Override
    public String getPrompt(String text) {
        return POMPT_STRING
                .replace("{text}", text)
                .replace("{intents}", INTENT.toString());
    }

    @Override
    public Class<?> getReturnType() {
        return BodyResponseList.class;
    }

    @Override
    public void processResult(Object result) {
        // TODO: Implementar o processamento do resultado.
        if (result instanceof BodyResponseList response) {
            System.out.println(response.measures());
        }
    }
}
