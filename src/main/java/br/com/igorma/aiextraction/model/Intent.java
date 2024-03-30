package br.com.igorma.aiextraction.model;

import jakarta.annotation.Nonnull;

import java.util.stream.Stream;

public enum Intent {
    HEIGHT("altura", IntentType.BODY, Double.class),
    WEIGHT("peso", IntentType.BODY, Double.class),
    HIP_WIDTH("largura_quadril", IntentType.BODY, Double.class),
    SHOULDER_WIDTH("largura_ombros", IntentType.BODY, Double.class),
    MILEAGE("quilometragem", IntentType.CAR, Integer.class);

    private final String intentPt;
    private final IntentType intentType;
    private final Class<? extends Number> type;

    Intent(String intent, IntentType intentType, Class<? extends Number> type) {
        this.intentPt = intent;
        this.intentType = intentType;
        this.type = type;
    }

    public static String listIntentsAndTypesInPortuguese() {
        return Stream.of(Intent.values())
                .map(e -> e.intentPt + " (" + e.type.getSimpleName() + ")")
                .toList()
                .toString();
    }

    public static String listIntentsAndTypesInPortuguese(@Nonnull IntentType intentType) {
        return Stream.of(Intent.values())
                .filter(e -> e.intentType == intentType)
                .map(e -> e.intentPt + " (" + e.type.getSimpleName() + ")")
                .toList()
                .toString();
    }
}
