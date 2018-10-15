package devlight.edu.conference.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devlight.edu.conference.model.Curator;

@Repository
public interface CuratorRepository extends JpaRepository<Curator, Integer> {
	Optional<Curator> findByNameAndSurname(String name, String surname);

}
