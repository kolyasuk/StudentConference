package devlight.edu.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import devlight.edu.conference.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}
