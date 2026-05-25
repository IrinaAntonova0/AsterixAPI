package org.example.asterixapi.service;

import org.example.asterixapi.model.CharacterRecord;
import org.example.asterixapi.repository.CharacterRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AsterixService {

    private final CharacterRepo characterRepo;

    public AsterixService(CharacterRepo characterRepo) {
        this.characterRepo = characterRepo;
    }

    public static final List<CharacterRecord> CHARACTER_RECORDS = new ArrayList<>(
            List.of(
                    new CharacterRecord("1", "Asterix", 35, "Warrior"),
                    new CharacterRecord("2", "Obelix", 35, "Supplier"),
                    new CharacterRecord("3", "Miraculix", 60, "Druid"),
                    new CharacterRecord("4", "Majestix", 60, "Chief"),
                    new CharacterRecord("5", "Troubadix", 25, "Bard"),
                    new CharacterRecord("6", "Gutemine", 35, "Chiefs Wife"),
                    new CharacterRecord("7", "Idefix", 5, "Dog"),
                    new CharacterRecord("8", "Geriatrix", 70, "Retiree"),
                    new CharacterRecord("9", "Automatix", 35, "Smith"),
                    new CharacterRecord("10", "Grockelix", 35, "Fisherman")
            ));

    public String helloGallier(){
        return "Hello Gallier!";
    }

    public List<CharacterRecord> printAllCharacters(){
        return CHARACTER_RECORDS;
    }

    public List<CharacterRecord> loadcharacters(){
        return characterRepo.insert(CHARACTER_RECORDS);
    }

    public List<CharacterRecord> getCharacters(){
        return characterRepo.findAll();
    }

    public List<CharacterRecord> getCharactersBy( String name, String age, String profession) {

        if(name!=null && !name.isEmpty()) {
            return characterRepo.findAllByName(name);
        }
        if (age!=null && !age.isEmpty()) {
            return characterRepo.findAllByAge(Integer.parseInt(age));
        }
        if (profession!=null && !profession.isEmpty()) {
            return characterRepo.findAllByProfession(profession);
        }
        return characterRepo.findAll();
    }

    public Double getAverageAgeByProfession(String profession) {
        return characterRepo.findAllByProfession(profession).stream()
                .mapToInt(CharacterRecord::age)
                .average()
                .orElse(0.0);
    }

    public Optional<CharacterRecord> postCharacter( CharacterRecord characterRecord){
        return Optional.of(characterRepo.insert(characterRecord));
    }

    public Optional<CharacterRecord> putCharacter(String id, CharacterRecord characterRecord) {
        return Optional.of( characterRepo.existsById(id) ? characterRepo.save(characterRecord) : null );
    }

    public void deleteCharacter(String id) {
        characterRepo.deleteById(id);
    }
}
