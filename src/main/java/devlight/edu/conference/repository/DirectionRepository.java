package devlight.edu.conference.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devlight.edu.conference.model.Direction;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Integer> {
	Optional<Direction> findByDirectionName(String name);
}
