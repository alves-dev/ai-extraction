package br.com.igorma.aiextraction.service;

import br.com.igorma.aiextraction.model.IntentResponseList;

public interface IntentExtractionService {

  IntentResponseList speechToText(String text);
}
