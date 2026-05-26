package org.example.asterixapi.service;

import org.example.asterixapi.dto.CharacterRecordDTO;
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
    private final CharacterRecordIdService characterRecordIdService;

    public AsterixService(CharacterRepo characterRepo, CharacterRecordIdService characterRecordIdService) {
        this.characterRepo = characterRepo;
        this.characterRecordIdService = characterRecordIdService;
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

    public Optional<CharacterRecord> getCharacterBy(String id) {
        return characterRepo.findById(id);
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

    public Optional<CharacterRecord> postCharacter(CharacterRecordDTO characterRecorddto){
        String newID = "";
        do{
            newID = this.characterRecordIdService.generateNewCharacterId();
        } while(this.characterRepo.existsById(newID));

        CharacterRecord crn = CharacterRecord.builder()
            .id(newID)
                .name(characterRecorddto.name())
                .age(characterRecorddto.age())
                .profession(characterRecorddto.profession())
                .build();
        //CharacterRecord cr = new CharacterRecord(newID, characterRecorddto.name(), characterRecorddto.age(), characterRecorddto.profession());

        return Optional.of(characterRepo.insert(crn));
    }

    public Optional<CharacterRecord> putCharacter(String id, CharacterRecordDTO characterRecordDTO) {
        if(characterRepo.existsById(id)){
            CharacterRecord crNew = new CharacterRecord(id, characterRecordDTO.name(), characterRecordDTO.age(), characterRecordDTO.profession());
            return Optional.of(characterRepo.save(crNew));
        }
        else {
            return Optional.empty();
        }
    }

    public void deleteCharacter(String id) {
        characterRepo.deleteById(id);
    }
}
