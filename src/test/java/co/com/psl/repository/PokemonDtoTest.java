package co.com.psl.repository;

import co.com.psl.model.Pokemon;
import co.com.psl.model.Type;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PokemonDtoTest {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired TypeRepository typeRepository;

    @Test
    public void typeRepositoryShouldPersistType() {
        Type type = new Type("fairy");
        Long id = type.getId();
        typeRepository.save(type);
        Assert.assertNotEquals(id, type.getId());
    }

    @Test
    public void pokemonRepositoryShouldPersistPokemon() {
        Pokemon pokemon = new Pokemon("Charmander", typeRepository.findByNameContaining("fire"), typeRepository.findByNameContaining("water"), null, "http://cdn.bulbagarden.net/upload/7/73/004Charmander.png");
        Long id = pokemon.getId();
        pokemonRepository.save(pokemon);
        Assert.assertNotEquals(id, pokemon.getId());
    }
}