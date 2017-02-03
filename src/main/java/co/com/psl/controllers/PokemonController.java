package co.com.psl.controllers;

import co.com.psl.config.PokemonSetup;
import co.com.psl.dto.PokemonDTO;
import co.com.psl.models.Pokemon;
import co.com.psl.models.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by jvanegasp on 1/02/2017.
 */
@RestController
public class PokemonController {

    @Autowired
    private PokemonSetup pokemonRepository = new PokemonSetup();

    @RequestMapping(value = "/pokemon", method = RequestMethod.GET)
    public ResponseEntity<List<PokemonDTO>> getAllPokemon(@RequestParam(value = "name", required = false) String name) {
        if (name == null || name.isEmpty()) {
            List<PokemonDTO> pokemonDTOList = new ArrayList<>();
            for (Pokemon pokemon:pokemonRepository.getExistingPokemon()) {
                pokemonDTOList.add(convertToDto(pokemon));
            }
            return new ResponseEntity<>(pokemonDTOList, HttpStatus.OK);
        }
        return new ResponseEntity<>(searchPokemonByName(name).stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @RequestMapping(value = "/pokemon/{id}", method = RequestMethod.GET)
    public ResponseEntity<PokemonDTO> getSinglePokemon(@PathVariable(value = "id") Long id) {
        Pokemon pokemonFound = searchPokemonById(id);
        if (pokemonFound == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(convertToDto(pokemonFound), HttpStatus.OK);
    }

    @RequestMapping(value = "/pokemon/types", method = RequestMethod.GET)
    public ResponseEntity<List<Type>> getAllTypes(@RequestParam(value = "name", required = false) String name) {
        if (name == null || name.isEmpty())
            return new ResponseEntity<>(pokemonRepository.getExistingTypes(), HttpStatus.OK);
        return new ResponseEntity<>(searchTypeByName(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/pokemon/types/{id}", method = RequestMethod.GET)
    public ResponseEntity<Type> getSingleType(@PathVariable(value = "id") Long id) {
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

    private Pokemon searchPokemonById(Long id) {
        for (Pokemon pokemon: pokemonRepository.getExistingPokemon()) {
            if (Objects.equals(pokemon.getId(), id)) {
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

    private Type searchTypeById(Long id) {
        for (Type type: pokemonRepository.getExistingTypes()) {
            if (Objects.equals(type.getId(), id)) {
                return type;
            }
        }
        return null;
    }

    private PokemonDTO convertToDto(Pokemon pokemon) {
        List<String> typesNameList = new ArrayList<>();
        List<String> weaknessesNameList = new ArrayList<>();
        for (Type type:pokemon.getType()) typesNameList.add(type.getName());
        for (Type type:pokemon.getWeakness()) weaknessesNameList.add(type.getName());
        return new PokemonDTO(pokemon.getId().toString(), pokemon.getName(), typesNameList, weaknessesNameList, pokemon.getEvolutionId().toString(), pokemon.getImage());
    }
}
