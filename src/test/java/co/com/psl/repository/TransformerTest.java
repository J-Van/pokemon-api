package co.com.psl.repository;

import co.com.psl.domain.Pokemon;
import co.com.psl.domain.PokemonDto;
import co.com.psl.domain.Type;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TransformerTest {

    private PokemonDtoTransformer transformer;

    @Before
    public void initTransformer() {
        transformer = new PokemonDtoTransformer();
    }

    @Test
    public void covertToDtoShouldReturnAPokemonDto() {
        List<Type> pokemonTypes = Arrays.asList(new Type("water"), new Type("dark"));
        List<Type> pokemonWeaknesses = Arrays.asList(new Type("fighting"), new Type("bug"), new Type("grass"), new Type("electric"), new Type("fairy"));
        Pokemon pokemonActual = new Pokemon("Greninja", pokemonTypes, pokemonWeaknesses, null, "image_placeholder");
        pokemonActual.setId(0L);
        PokemonDto pokemonExpected = new PokemonDto("0", "Greninja", Arrays.asList("water", "dark"), Arrays.asList("fighting", "bug", "grass", "electric", "fairy"), null, "image_placeholder");
        Assert.assertEquals(pokemonExpected, transformer.convertToDto(pokemonActual));
    }
}
