package org.example.asterixapi.controller;

import org.example.asterixapi.model.CharacterRecord;
import org.example.asterixapi.service.AsterixService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asterixapi")
public class AsterixController {

    private final AsterixService asterixService;

    public AsterixController(AsterixService asterixService) {
        this.asterixService = asterixService;
    }

    @GetMapping("/gallien")
    public String helloGallier(){
        return asterixService.helloGallier();
    }

    @GetMapping("/characters")
    public List<CharacterRecord> printAllCharacters(){
        return asterixService.printAllCharacters();
    }

    @GetMapping("/loadcharacters")
    public List<CharacterRecord> loadcharacters(){
        return asterixService.loadcharacters();
    }

    @GetMapping("/getcharacters")
    public List<CharacterRecord> getCharacters(){
        return asterixService.getCharacters();
    }

    @GetMapping("/getcharacters/by")//localhost:8080/asterixapi/getcharacters/by?name=iri&age=23&profession=Bard
    public List<CharacterRecord> getCharactersBy(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) String profession) {

        return asterixService.getCharactersBy(name, age, profession);
    }

    @GetMapping("/getaverageage/{profession}")
    public Double getAverageAgeByProfession(@PathVariable String profession) {
        return asterixService.getAverageAgeByProfession(profession);
    }

    @PostMapping("/postcharacter")
    public Optional<CharacterRecord> postCharacter(@RequestBody CharacterRecord characterRecord){
        return asterixService.postCharacter(characterRecord);
    }

    @PutMapping("/putcharacter/{id}")
    public Optional<CharacterRecord> putCharacter(@PathVariable String id, @RequestBody CharacterRecord characterRecord) {
        return asterixService.putCharacter( id, characterRecord);
    }

    @DeleteMapping("/deletecharacter/{id}")
    public void deleteCharacter(@PathVariable String id) {
        asterixService.deleteCharacter(id);
    }
}
