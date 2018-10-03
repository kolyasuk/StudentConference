package devlight.edu.conference.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Direction {

	@Id
	@GeneratedValue
	private int id;
	@NotBlank
	@Size(min = 2, message = "Direction name must be longer than 2")
	private String directionName;
	@OneToMany(mappedBy = "direction", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Application> application;
}
