package co.com.psl.controllers;

import co.com.psl.config.PokemonSetup;
import co.com.psl.models.Pokemon;
import co.com.psl.models.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

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
    public ResponseEntity<List<Pokemon>> getAllPokemon(@RequestParam(value = "name", required = false) String name) {
        if (name == null || name.isEmpty()) {
            return new ResponseEntity<>(pokemonRepository.getExistingPokemon(), HttpStatus.OK);
        }
        return new ResponseEntity<>(searchPokemonByName(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/pokemon/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pokemon> getSinglePokemon(@PathVariable(value = "id") String id) {
        Pokemon pokemonFound = searchPokemonById(id);
        if (pokemonFound == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pokemonFound, HttpStatus.OK);
    }

    @RequestMapping(value = "/pokemon/types", method = RequestMethod.GET)
    public ResponseEntity<List<Type>> getAllTypes(@RequestParam(value = "name", required = false) String name) {
        if (name == null || name.isEmpty()) {
            return new ResponseEntity<>(pokemonRepository.getExistingTypes(), HttpStatus.OK);
        }
        return new ResponseEntity<>(searchTypeByName(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/pokemon/types/{id}", method = RequestMethod.GET)
    public ResponseEntity<Type> getSingleType(@PathVariable(value = "id") String id) {
        Type typeFound = searchTypeById(id);
        if (typeFound == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(typeFound, HttpStatus.OK);
    }

    private List<Pokemon> searchPokemonByName(String name) {
        List<Pokemon> pokemonByName = new ArrayList<>();
        String regexToSearch = ".*" + Pattern.quote(name) + ".*";
        for (Pokemon pokemon: pokemonRepository.getExistingPokemon()) {
            if (pokemon.getName().matches(regexToSearch)) {
                pokemonByName.add(pokemon);
            }
        }
        return pokemonByName;
    }

    private Pokemon searchPokemonById(String id) {
        for (Pokemon pokemon: pokemonRepository.getExistingPokemon()) {
            if (pokemon.getId().matches(id)) {
                return pokemon;
            }
        }
        return null;
    }

    private List<Type> searchTypeByName(String name) {
        List<Type> typeByName = new ArrayList<>();
        String regexToSearch = ".*" + Pattern.quote(name) + ".*";
        for (Type type: pokemonRepository.getExistingTypes()) {
            if (type.getName().matches(regexToSearch)) {
                typeByName.add(type);
            }
        }
        return typeByName;
    }

    private Type searchTypeById(String id) {
        for (Type type: pokemonRepository.getExistingTypes()) {
            if (type.getId().matches(id)) {
                return type;
            }
        }
        return null;
    }
}
