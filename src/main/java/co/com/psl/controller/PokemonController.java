package co.com.psl.controller;

import co.com.psl.repository.PokemonDto;
import co.com.psl.model.Pokemon;
import co.com.psl.model.Type;
import co.com.psl.repository.PokemonRepository;
import co.com.psl.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PokemonController {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private TypeRepository typeRepository;

    @RequestMapping(value = "/pokemon", method = RequestMethod.GET)
    public ResponseEntity<List<PokemonDto>> getAllPokemon(@RequestParam(value = "name", required = false) String name) {
        if (name == null || name.isEmpty()) {
            return new ResponseEntity<>(convertPokemonListToDtoList(pokemonRepository.findAll()), HttpStatus.OK);
        }
        return new ResponseEntity<>(convertPokemonListToDtoList(pokemonRepository.findByNameContaining(name)), HttpStatus.OK);
    }

    @RequestMapping(value = "/pokemon/{id}", method = RequestMethod.GET)
    public ResponseEntity<PokemonDto> getSinglePokemon(@PathVariable(value = "id") Long id) {
        Pokemon pokemonFound = pokemonRepository.findById(id);
        if (pokemonFound == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(convertToDto(pokemonFound), HttpStatus.OK);
    }

    @RequestMapping(value = "/pokemon/types", method = RequestMethod.GET)
    public ResponseEntity<List<Type>> getAllTypes(@RequestParam(value = "name", required = false) String name) {
        if (name == null || name.isEmpty())
            return new ResponseEntity<>(typeRepository.findAll(), HttpStatus.OK);
        return new ResponseEntity<>(typeRepository.findByNameContaining(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/pokemon/types/{id}", method = RequestMethod.GET)
    public ResponseEntity<Type> getSingleType(@PathVariable(value = "id") Long id) {
        Type typeFound = typeRepository.findById(id);
        if (typeFound == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(typeFound, HttpStatus.OK);
    }

    private List<PokemonDto> convertPokemonListToDtoList(List<Pokemon> pokemonList) {
        return pokemonList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private PokemonDto convertToDto(Pokemon pokemon) {
        List<String> typesNameList = new ArrayList<>();
        List<String> weaknessesNameList = new ArrayList<>();
        for (Type type:pokemon.getType()) typesNameList.add(type.getName());
        for (Type type:pokemon.getWeakness()) weaknessesNameList.add(type.getName());
        String pokemonEvolutionId = null;
        if (pokemon.getEvolution() != null) pokemonEvolutionId = pokemon.getEvolution().getId().toString();
        return new PokemonDto(pokemon.getId().toString(), pokemon.getName(), typesNameList, weaknessesNameList, pokemonEvolutionId, pokemon.getImage());
    }
}
