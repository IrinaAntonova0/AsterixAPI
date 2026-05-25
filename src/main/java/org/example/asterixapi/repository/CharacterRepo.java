package org.example.asterixapi.repository;

import org.example.asterixapi.model.CharacterRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CharacterRepo extends MongoRepository<CharacterRecord,String> {

    List<CharacterRecord> findAllByName(String name);

    List<CharacterRecord> findAllByAge(int age);

    List<CharacterRecord> findAllByProfession(String profession);
}
