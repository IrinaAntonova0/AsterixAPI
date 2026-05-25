package org.example.asterixapi.controller;

import org.example.asterixapi.model.CharacterRecord;
import org.example.asterixapi.repository.CharacterRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asterixapi")
public class AsterixController {

    private final CharacterRepo characterRepo;

    public AsterixController(CharacterRepo characterRepo) {
        this.characterRepo = characterRepo;
    }

    public static final List<CharacterRecord> CHARACTER_RECORDS = new ArrayList<CharacterRecord>(
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

    @GetMapping("/gallien")
    public String helloGallier(){
        return "Hello Gallier!";
    }

    @GetMapping("/characters")
    public List<CharacterRecord> printAllCharacters(){
        return CHARACTER_RECORDS;
    }

    @GetMapping("/loadcharacters")
    public List<CharacterRecord> loadcharacters(){
        return characterRepo.insert(CHARACTER_RECORDS);
    }

    @GetMapping("/getcharacters")
    public List<CharacterRecord> getCharacters(){
        return characterRepo.findAll();
    }

    @GetMapping("/getcharacters/by")//localhost:8080/asterixapi/getcharacters/by?name=iri&age=23&profession=Bard
    public List<CharacterRecord> getCharactersBy(
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String age,
                            @RequestParam(required = false) String profession) {

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

    @GetMapping("/getaverageage/{profession}")
    public Double getAverageAgeByProfession(@PathVariable String profession) {
        return characterRepo.findAllByProfession(profession).stream()
                .mapToInt(CharacterRecord::age)
                .average()
                .orElse(0.0);
    }

    @PostMapping("/postcharacter")
    public Optional<CharacterRecord> postCharacter(@RequestBody CharacterRecord characterRecord){
        return Optional.of(characterRepo.insert(characterRecord));
    }

    @PutMapping("/putcharacter/{id}")
    public Optional<CharacterRecord> putCharacter(@PathVariable String id, @RequestBody CharacterRecord characterRecord) {
        return Optional.of( characterRepo.existsById(id) ? characterRepo.save(characterRecord) : null );
    }

    @DeleteMapping("/deletecharacter/{id}")
    public void deleteCharacter(@PathVariable String id) {
        characterRepo.deleteById(id);
    }
}
