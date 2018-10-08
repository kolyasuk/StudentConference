package devlight.edu.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devlight.edu.conference.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}
