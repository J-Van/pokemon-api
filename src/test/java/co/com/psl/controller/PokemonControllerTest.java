package co.com.psl.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllPokemon() throws Exception {
        mockMvc.perform(get("/pokemon"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].type").exists())
                .andExpect(jsonPath("$[0].weakness").exists())
                .andExpect(jsonPath("$[0].evolutionId").exists())
                .andExpect(jsonPath("$[0].image").exists()
                );
    }

    @Test
    public void searchPokemonByIkaShouldReturnPikachu() throws Exception {
        mockMvc.perform(get("/pokemon").param("name", "ika"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Pikachu")
                );
    }

    @Test
    public void searchPokemonBySaurShouldReturnBulbasaur() throws Exception {
        mockMvc.perform(get("/pokemon").param("name", "saur"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$[0].id").value("2"))
                .andExpect(jsonPath("$[0].name").value("Bulbasaur")
                );
    }

    @Test
    public void getSinglePokemon() throws Exception {
        mockMvc.perform(get("/pokemon/1"))
                .andExpect(status().isOk())
                        .andExpect(jsonPath("$").exists())
                        .andExpect(jsonPath("$.id").value("1"))
                        .andExpect(jsonPath("$.name").value("Pikachu")
                );
    }

    @Test
    public void searchForNonExistingPokemonShould404() throws Exception {
        mockMvc.perform(get("/pokemon/104"))
                .andExpect(status().isNotFound()
                );
    }

    @Test
    public void getAllTypes() throws Exception {
        mockMvc.perform(get("/pokemon/types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(9)))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists()
                );
    }

    @Test
    public void searchTypesByIcShouldReturnAllFound() throws Exception {
        mockMvc.perform(get("/pokemon/types").param("name", "ic"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value("7"))
                .andExpect(jsonPath("$[0].name").value("electric")
                );
    }

    @Test
    public void searchTypesBySychShouldReturnPsychic() throws Exception {
        mockMvc.perform(get("/pokemon/types").param("name", "sych"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value("8"))
                .andExpect(jsonPath("$[0].name").value("psychic")
                );
    }

    @Test
    public void getSingleType() throws Exception {
        mockMvc.perform(get("/pokemon/types/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("normal")
                );
    }

}
