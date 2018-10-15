package devlight.edu.conference.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import devlight.edu.conference.model.Marks;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Integer> {
	List<Marks> findAllByApplicationId(int applicationId);

	@Query("SELECT AVG(m.mark) from Marks m where application_id =?1")
	double getAverageMark(int application_id);
}
