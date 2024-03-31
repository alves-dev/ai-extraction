package br.com.igorma.aiextraction.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class ThemeExtractionProcessor {

    private final ApplicationContext applicationContext;

    public ThemeExtractionProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public List<String> getThemesExtraction() {
        Map<String, ThemeExtraction> beansOfType = applicationContext.getBeansOfType(ThemeExtraction.class);

        return beansOfType.values().stream()
                .map(ThemeExtraction::getTheme)
                .toList();
    }

    public ThemeExtraction getThemeExtraction(String theme) {
        Map<String, ThemeExtraction> beansOfType = applicationContext.getBeansOfType(ThemeExtraction.class);

        for (String beanName : beansOfType.keySet()) {
            ThemeExtraction themeExtraction = beansOfType.get(beanName);
            if (Objects.equals(theme, themeExtraction.getTheme())) {
                return themeExtraction;
            }
        }
        throw new IllegalArgumentException("theme value is invalid!");
    }
}
