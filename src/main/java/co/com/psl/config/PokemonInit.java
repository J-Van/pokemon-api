package co.com.psl.config;

import co.com.psl.model.Pokemon;
import co.com.psl.model.Type;
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
        typeRepository.save(new Type(2L, "flying"));
        typeRepository.save(new Type(3L, "poison"));
        typeRepository.save(new Type(4L, "ground"));
        typeRepository.save(new Type(5L, "fire"));
        typeRepository.save(new Type(6L, "grass"));
        typeRepository.save(new Type(7L, "electric"));
        typeRepository.save(new Type(8L, "psychic"));
        typeRepository.save(new Type(9L, "ice"));
        typeRepository.flush();

        List<Type> pikachuTypes = new ArrayList<>();
        pikachuTypes.add(typeRepository.findById(7L));
        List<Type> pikachuWeaknesses = new ArrayList<>();
        pikachuWeaknesses.add(typeRepository.findById(4L));
        Pokemon pikachu = new Pokemon(1L, "Pikachu", pikachuTypes, pikachuWeaknesses, null, "http://cdn.bulbagarden.net/upload/0/0d/025Pikachu.png");

        List<Type> bulbasaurTypes = new ArrayList<>();
        bulbasaurTypes.add(typeRepository.findById(6L));
        bulbasaurTypes.add(typeRepository.findById(3L));
        List<Type> bulbasaurWeaknesses = new ArrayList<>();
        bulbasaurWeaknesses.add(typeRepository.findById(5L));
        bulbasaurWeaknesses.add(typeRepository.findById(9L));
        bulbasaurWeaknesses.add(typeRepository.findById(2L));
        bulbasaurWeaknesses.add(typeRepository.findById(8L));
        Pokemon bulbasaur = new Pokemon(2L, "Bulbasaur", bulbasaurTypes, bulbasaurWeaknesses, null, "http://cdn.bulbagarden.net/upload/2/21/001Bulbasaur.png");
        Pokemon raichu = new Pokemon(3L, "Raichu", pikachuTypes, pikachuWeaknesses, null, "http://cdn.bulbagarden.net/upload/8/88/026Raichu.png");
        pokemonRepository.save(pikachu);
        pokemonRepository.save(bulbasaur);
        pokemonRepository.save(raichu);
        pokemonRepository.flush();
    }
}
