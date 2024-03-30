package br.com.igorma.aiextraction.model;

import java.util.Arrays;
import java.util.List;

public enum IntentType {
    BODY("Medidas Corporais"),
    CAR("Carro");

    private final String typePt;

    IntentType(String typePt) {
        this.typePt = typePt;
    }

    private String getType() {
        return typePt;
    }

    public static List<String> getTypes() {
        return Arrays.stream(IntentType.values()).map(IntentType::getType).toList();
    }

    public static IntentType valuePt(String typePt) {
        return Arrays.stream(
                IntentType.values())
                .filter(i -> i.getType().equals(typePt))
                .findFirst().orElseThrow();
    }
}
