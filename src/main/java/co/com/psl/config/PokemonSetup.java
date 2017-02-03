package co.com.psl.config;

import co.com.psl.models.Pokemon;
import co.com.psl.models.Type;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvanegasp on 1/02/2017.
 */

@Component
public class PokemonSetup {

    private List<Type> existingTypes = new ArrayList<>();
    private List<Pokemon> existingPokemon = new ArrayList<>();

    @PostConstruct
    public void pokemonSetup() {
        System.out.println("Setup Pokemon");
        existingTypes.add(new Type(1L, "normal"));
        existingTypes.add(new Type(2L, "flying"));
        existingTypes.add(new Type(3L, "poison"));
        existingTypes.add(new Type(4L, "ground"));
        existingTypes.add(new Type(5L, "fire"));
        existingTypes.add(new Type(6L, "grass"));
        existingTypes.add(new Type(7L, "electric"));
        existingTypes.add(new Type(8L, "psychic"));
        existingTypes.add(new Type(9L, "ice"));

        List<Type> pikachuTypes = new ArrayList<>();
        pikachuTypes.add(existingTypes.get(6));
        List<Type> pikachuWeaknesses = new ArrayList<>();
        pikachuWeaknesses.add(existingTypes.get(3));
        existingPokemon.add(new Pokemon(1L, "Pikachu", pikachuTypes, pikachuWeaknesses, 3L, "http://cdn.bulbagarden.net/upload/0/0d/025Pikachu.png"));

        List<Type> bulbasaurTypes = new ArrayList<>();
        bulbasaurTypes.add(existingTypes.get(5));
        bulbasaurTypes.add(existingTypes.get(2));
        List<Type> bulbasaurWeaknesses = new ArrayList<>();
        bulbasaurWeaknesses.add(existingTypes.get(4));
        bulbasaurWeaknesses.add(existingTypes.get(8));
        bulbasaurWeaknesses.add(existingTypes.get(1));
        bulbasaurWeaknesses.add(existingTypes.get(7));
        existingPokemon.add(new Pokemon(2L, "Bulbasaur", bulbasaurTypes, bulbasaurWeaknesses, 0L, "http://cdn.bulbagarden.net/upload/2/21/001Bulbasaur.png"));
    }

    public List<Type> getExistingTypes() {
        return existingTypes;
    }

    public void setExistingTypes(ArrayList<Type> existingTypes) {
        this.existingTypes = existingTypes;
    }

    public List<Pokemon> getExistingPokemon() {
        return existingPokemon;
    }

    public void setExistingPokemon(ArrayList<Pokemon> existingPokemon) {
        this.existingPokemon = existingPokemon;
    }
}
