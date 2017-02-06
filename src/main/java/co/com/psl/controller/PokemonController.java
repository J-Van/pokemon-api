package co.com.psl.controller;

import co.com.psl.domain.Pokemon;
import co.com.psl.domain.PokemonDto;
import co.com.psl.domain.Type;
import co.com.psl.repository.PokemonDtoTransformer;
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
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private PokemonDtoTransformer transformer;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PokemonDto>> getAllPokemon(@RequestParam(value = "name", required = false) String name) {
        if (name == null || name.isEmpty()) {
            return new ResponseEntity<>(transformer.convertPokemonListToDtoList(pokemonRepository.findAll()), HttpStatus.OK);
        }
        return new ResponseEntity<>(transformer.convertPokemonListToDtoList(pokemonRepository.findByNameContaining(name)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PokemonDto> getSinglePokemon(@PathVariable(value = "id") Long id) {
        Pokemon pokemonFound = pokemonRepository.findById(id);
        if (pokemonFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transformer.convertToDto(pokemonFound), HttpStatus.OK);
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public ResponseEntity<List<Type>> getAllTypes(@RequestParam(value = "name", required = false) String name) {
        if (name == null || name.isEmpty())
            return new ResponseEntity<>(typeRepository.findAll(), HttpStatus.OK);
        return new ResponseEntity<>(typeRepository.findByNameContaining(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/types/{id}", method = RequestMethod.GET)
    public ResponseEntity<Type> getSingleType(@PathVariable(value = "id") Long id) {
        Type typeFound = typeRepository.findById(id);
        if (typeFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(typeFound, HttpStatus.OK);
    }
}
