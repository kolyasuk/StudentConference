package devlight.edu.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devlight.edu.conference.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {

}
