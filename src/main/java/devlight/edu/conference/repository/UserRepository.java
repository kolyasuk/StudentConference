package devlight.edu.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import devlight.edu.conference.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
