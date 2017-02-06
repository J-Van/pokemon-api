package co.com.psl.repository;

import co.com.psl.domain.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    List<Pokemon> findByNameContaining(String name);

    Pokemon findById(Long id);
}
