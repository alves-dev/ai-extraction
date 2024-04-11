package br.com.igorma.aiextraction.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDService {

    @Value("${persson.uuid}")
    private UUID personId;

    public UUID getPersonId() {
        return personId;
    }
}
