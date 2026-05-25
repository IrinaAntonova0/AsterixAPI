package org.example.asterixapi.repository;

import org.example.asterixapi.model.CharacterRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CharacterRepo extends MongoRepository<CharacterRecord,String> {

}
