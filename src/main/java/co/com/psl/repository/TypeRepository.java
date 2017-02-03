package co.com.psl.repository;

import co.com.psl.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long> {

    List<Type> findByNameContaining(String name);

    Type findById(Long id);
}
