package co.com.psl.config;

import co.com.psl.domain.Pokemon;
import co.com.psl.domain.Type;
import co.com.psl.repository.PokemonRepository;
import co.com.psl.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class PokemonInit {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private TypeRepository typeRepository;

    @PostConstruct
    public void pokemonSetup() {
        System.out.println("Setup Pokemon");
        typeRepository.save(new Type(1L, "normal"));
        typeRepository.save(new Type(2L, "fighting"));
        typeRepository.save(new Type(3L, "flying"));
        typeRepository.save(new Type(4L, "poison"));
        typeRepository.save(new Type(5L, "ground"));
        typeRepository.save(new Type(6L, "rock"));
        typeRepository.save(new Type(7L, "bug"));
        typeRepository.save(new Type(8L, "ghost"));
        typeRepository.save(new Type(9L, "steel"));
        typeRepository.save(new Type(10L, "fire"));
        typeRepository.save(new Type(11L, "water"));
        typeRepository.save(new Type(12L, "grass"));
        typeRepository.save(new Type(13L, "electric"));
        typeRepository.save(new Type(14L, "psychic"));
        typeRepository.save(new Type(15L, "ice"));
        typeRepository.save(new Type(16L, "dragon"));
        typeRepository.save(new Type(17L, "dark"));
        typeRepository.save(new Type(18L, "fairy"));
        typeRepository.flush();

        List<Type> pikachuTypes = new ArrayList<>();
        pikachuTypes.add(typeRepository.findById(13L));
        List<Type> pikachuWeaknesses = new ArrayList<>();
        pikachuWeaknesses.add(typeRepository.findById(5L));
        Pokemon pikachu = new Pokemon("Pikachu", pikachuTypes, pikachuWeaknesses, null, "http://cdn.bulbagarden.net/upload/0/0d/025Pikachu.png");

        List<Type> bulbasaurTypes = new ArrayList<>();
        bulbasaurTypes.add(typeRepository.findById(12L));
        bulbasaurTypes.add(typeRepository.findById(4L));
        List<Type> bulbasaurWeaknesses = new ArrayList<>();
        bulbasaurWeaknesses.add(typeRepository.findById(3L));
        bulbasaurWeaknesses.add(typeRepository.findById(10L));
        bulbasaurWeaknesses.add(typeRepository.findById(14L));
        bulbasaurWeaknesses.add(typeRepository.findById(15L));
        Pokemon bulbasaur = new Pokemon("Bulbasaur", bulbasaurTypes, bulbasaurWeaknesses, null, "http://cdn.bulbagarden.net/upload/2/21/001Bulbasaur.png");
        Pokemon raichu = new Pokemon("Raichu", pikachuTypes, pikachuWeaknesses, null, "http://cdn.bulbagarden.net/upload/8/88/026Raichu.png");
        pokemonRepository.save(pikachu);
        pokemonRepository.save(bulbasaur);
        pokemonRepository.save(raichu);
        pikachu.setEvolution(raichu);
        pokemonRepository.saveAndFlush(pikachu);
    }
}
