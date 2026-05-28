package org.example.asterixapi.controller;

import org.example.asterixapi.model.CharacterRecord;
import org.example.asterixapi.repository.CharacterRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AsterixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CharacterRepo characterRepo;

    @Test
    void postCharacter() {
        //Given
/*        CharacterRecordDTO dto = new CharacterRecordDTO("name",23,"gallier");
        ObjectMapper oM = new ObjectMapper();
        String reqJson = oM.writeValueAsString(dto);*/
    }

    @Test
    void getCharacters() throws Exception {
        //Given
        CharacterRecord charRec = CharacterRecord.builder().id("3").name("name").age(23).profession("profess").build();
        characterRepo.insert(charRec);
        //When
        mockMvc.perform(MockMvcRequestBuilders.get("/asterixapi/getcharacters"))
                //Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        [
                        {
                            "id": "3",
                            "name": "name",
                            "age": 23,
                            "profession": "profess"
                        }
                        ]
                        """));
    }

    @Test
    void getCharacter() throws Exception {
        //Given
        CharacterRecord charRec = CharacterRecord.builder().id("1").name("name").age(23).profession("profess").build();
        characterRepo.insert(charRec);
        //When
        mockMvc.perform(MockMvcRequestBuilders.get("/asterixapi/getcharacter/1"))
                //Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "id": "1",
                            "name": "name",
                            "age": 23,
                            "profession": "profess"
                        }
"""));
    }

    @Test
    void getCharacter_emptyDB() throws Exception {
        //Given
        CharacterRecord charRec = CharacterRecord.builder().id("1").name("name").age(23).profession("profess").build();
        //When
        mockMvc.perform(MockMvcRequestBuilders.get("/asterixapi/getcharacter/1"))
                //Then
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string("null"));

    }
}