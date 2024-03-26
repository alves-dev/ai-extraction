package br.com.igorma.aiextraction.model;

import java.util.stream.Stream;

public enum Intent {
    HEIGHT("altura", Double.class),
    WEIGHT("peso", Double.class),
    HIP_WIDTH("largura_quadril", Double.class),
    SHOULDER_WIDTH("largura_ombros", Double.class),
    MILEAGE("quilometragem", Integer.class);

    private final String intentPt;
    private final Class<? extends Number> type;

    Intent(String intent, Class<? extends Number> type) {
        this.intentPt = intent;
        this.type = type;
    }

    public static String listIntentsAndTypesInPortuguese() {
        return Stream.of(Intent.values()).map(e -> e.intentPt + " (" + e.type.getSimpleName() + ")")
            .toList().toString();
    }
}
