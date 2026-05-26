package org.example.asterixapi.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CharacterRecordIdService {

    private static String generateNewID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public String generateNewCharacterId() {
        return generateNewID();
    }

}
