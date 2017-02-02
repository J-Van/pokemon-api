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

    private ArrayList<Type> existingTypes;
    private ArrayList<Pokemon> existingPokemon;

    @PostConstruct
    public void pokemonSetup() {
        existingTypes.add(new Type(1, "normal"));
        existingTypes.add(new Type(2, "flying"));
        existingTypes.add(new Type(3, "poison"));
        existingTypes.add(new Type(4, "ground"));
        existingTypes.add(new Type(5, "fire"));
        existingTypes.add(new Type(6, "grass"));
        existingTypes.add(new Type(7, "electric"));
        existingTypes.add(new Type(8, "psychic"));
        existingTypes.add(new Type(9, "ice"));

        List<Type> pikachuTypes = new ArrayList<>();
        pikachuTypes.add(existingTypes.get(7));
        List<Type> pikachuWeaknesses = new ArrayList<>();
        pikachuWeaknesses.add(existingTypes.get(4));
        existingPokemon.add(new Pokemon(1, "Pikachu", pikachuTypes, pikachuWeaknesses, 3, "http://cdn.bulbagarden.net/upload/0/0d/025Pikachu.png"));

        List<Type> bulbasaurTypes = new ArrayList<>();
        bulbasaurTypes.add(existingTypes.get(6));
        bulbasaurTypes.add(existingTypes.get(3));
        List<Type> bulbasaurWeaknesses = new ArrayList<>();
        bulbasaurWeaknesses.add(existingTypes.get(5));
        bulbasaurWeaknesses.add(existingTypes.get(9));
        bulbasaurWeaknesses.add(existingTypes.get(2));
        bulbasaurWeaknesses.add(existingTypes.get(8));
        existingPokemon.add(new Pokemon(2, "Bulbasaur", bulbasaurTypes, bulbasaurWeaknesses, 0, "http://cdn.bulbagarden.net/upload/2/21/001Bulbasaur.png"));
    }
}
