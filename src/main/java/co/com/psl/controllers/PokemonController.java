package co.com.psl.controllers;

import co.com.psl.config.PokemonSetup;
import co.com.psl.models.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by jvanegasp on 1/02/2017.
 */
@RestController
public class PokemonController {

    @Autowired
    private PokemonSetup pokemonRepository = new PokemonSetup();

    @RequestMapping(value = "/pokemon", method = RequestMethod.GET)
    public List<Pokemon> getAllPokemon(@RequestParam(value = "name", required = false) String name) {
        if (name == null || name.isEmpty()) {
            return pokemonRepository.getExistingPokemon();
        }
        List<Pokemon> pokemonByName = new ArrayList<>();
        String regexToSearch = ".*" + Pattern.quote(name) + ".*";
        for (Pokemon pokemon: pokemonRepository.getExistingPokemon()) {
            if (pokemon.getName().matches(regexToSearch)) {
                pokemonByName.add(pokemon);
            }
        }
        return pokemonByName;
    }
}
