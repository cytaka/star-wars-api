package takahara.cynthia.starwars.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import takahara.cynthia.starwars.model.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, String> {
	@Query("{nome:{$regex:/?0/,$options: 'i'}}")
	List<Planeta> findByNome(String nome);
}
