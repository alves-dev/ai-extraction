package br.com.igorma.aiextraction.event;

public class EventSpeechToText extends EventLLMBase {
    private final String filePath;
    private final String text;

    public EventSpeechToText(String audioPath, String text) {
        super();
        this.filePath = audioPath;
        this.text = text;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getText() {
        return text;
    }
}
