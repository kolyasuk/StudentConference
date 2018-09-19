package devlight.edu.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import devlight.edu.conference.model.File;

public interface FileRepository extends JpaRepository<File, Integer> {

}
