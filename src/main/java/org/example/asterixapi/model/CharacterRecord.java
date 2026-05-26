package org.example.asterixapi.model;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("character")
@Builder
public record CharacterRecord(String id,
                              String name,
                              int age,
                              String profession) {
}
