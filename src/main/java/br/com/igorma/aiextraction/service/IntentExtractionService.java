package br.com.igorma.aiextraction.service;

import br.com.igorma.aiextraction.model.IntentResponseList;
import br.com.igorma.aiextraction.model.IntentType;

public interface IntentExtractionService {

    IntentResponseList intentExtractionToText(String text);

    IntentResponseList intentExtractionToText(String text, IntentType intentType);
}
