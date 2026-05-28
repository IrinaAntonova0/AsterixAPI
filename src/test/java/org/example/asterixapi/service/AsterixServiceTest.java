package org.example.asterixapi.service;

import org.example.asterixapi.dto.CharacterRecordDTO;
import org.example.asterixapi.model.CharacterRecord;
import org.example.asterixapi.repository.CharacterRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AsterixServiceTest {

    private CharacterRepo mockRepo = Mockito.mock(CharacterRepo.class);
    private CharacterRecordIdService mockIdService = mock(CharacterRecordIdService.class);

    @Test
    void getCharacters_whennocharacteravailable_returnemptylist() {
        //given
        List<CharacterRecord> crListExpected = new ArrayList<>();
        AsterixService testService = new AsterixService(mockRepo, mockIdService);
        when(mockRepo.findAll()).thenReturn(crListExpected);
        //when
        List<CharacterRecord> actual = testService.getCharacters();
        //then
        assertEquals(crListExpected,actual);
        verify(mockRepo, times(1)).findAll();
    }
    @Test
    void getCharacters_when1characteravailable_returnlistwiththis1() {
        //given
        CharacterRecord cr = new CharacterRecord("1","name",23,"profession");
        List<CharacterRecord> crListExpected = new ArrayList<>(List.of(cr));
        AsterixService testService = new AsterixService(mockRepo, mockIdService);
        when(mockRepo.findAll()).thenReturn(crListExpected);
        //when
        List<CharacterRecord> actual = testService.getCharacters();
        //then
        assertEquals(crListExpected,actual);
        verify(mockRepo, times(1)).findAll();
    }

    @Test
    void postCharacter() {
        //given
        CharacterRecordDTO crDTO = new CharacterRecordDTO("name",23,"profession");
        CharacterRecord expectedCharacterRecord = new CharacterRecord("1", "name", 23, "profession");
        Optional<CharacterRecord> expected = Optional.of(expectedCharacterRecord);
        AsterixService testService = new AsterixService(mockRepo, mockIdService);
        when(mockIdService.generateNewCharacterId()).thenReturn("1");
        when(mockRepo.existsById("1")).thenReturn(false);
        when(mockRepo.insert(any(CharacterRecord.class))).thenReturn(expectedCharacterRecord);
        //when
        Optional<CharacterRecord> actual = testService.postCharacter(crDTO);
        //then
        assertEquals(expected, actual);
        verify(mockIdService, times(1)).generateNewCharacterId();
        verify(mockRepo, times(1)).existsById(anyString());
        verify(mockRepo, times(1)).insert(expectedCharacterRecord);
    }

    @Test
    void getCharacterBy() {
    }
}