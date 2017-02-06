package co.com.psl.repository;

import co.com.psl.domain.Pokemon;
import co.com.psl.domain.PokemonDto;
import co.com.psl.domain.Type;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PokemonDtoTransformer {

    public List<PokemonDto> convertPokemonListToDtoList(List<Pokemon> pokemonList) {
        return pokemonList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public PokemonDto convertToDto(Pokemon pokemon) {
        List<String> typesNameList = new ArrayList<>();
        List<String> weaknessesNameList = new ArrayList<>();
        for (Type type : pokemon.getType()) typesNameList.add(type.getName());
        for (Type type : pokemon.getWeakness()) weaknessesNameList.add(type.getName());
        String pokemonEvolutionId = null;
        if (pokemon.getEvolution() != null) pokemonEvolutionId = pokemon.getEvolution().getId().toString();
        return new PokemonDto(pokemon.getId().toString(), pokemon.getName(), typesNameList, weaknessesNameList, pokemonEvolutionId, pokemon.getImage());
    }
}
