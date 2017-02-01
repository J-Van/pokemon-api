package co.com.psl.config;

import co.com.psl.models.Pokemon;
import co.com.psl.models.Type;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by jvanegasp on 1/02/2017.
 */

@Component
public class PokemonSetup {

    private ArrayList<Type> existingTypes;
    private ArrayList<Pokemon> existingPokemon;
}
