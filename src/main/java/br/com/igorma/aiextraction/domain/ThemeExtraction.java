package br.com.igorma.aiextraction.domain;

public interface ThemeExtraction {

    String getTheme();

    String getPrompt(String text);

    Class<?> getReturnType();

    void processResult(Object result);
}
