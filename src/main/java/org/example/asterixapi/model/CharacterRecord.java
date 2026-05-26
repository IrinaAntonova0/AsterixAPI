package org.example.asterixapi.model;

import org.springframework.data.mongodb.core.mapping.Document;


@Document("character")
public record CharacterRecord(String id,
                              String name,
                              int age,
                              String profession) {
}
