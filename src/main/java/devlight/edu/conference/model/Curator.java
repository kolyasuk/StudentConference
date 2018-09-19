package devlight.edu.conference.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Curator {

	@Id
	@GeneratedValue
	private int id;
	@NotBlank
	@Size(min = 2, message = "Name must be longer than 2")
	private String name;
	@NotBlank
	@Size(min = 2, message = "Surname must be longer than 2")
	private String surname;
	@NotNull
	private int photo_id;
	@NotBlank
	@Size(min = 10, message = "Description must be longer than 10")
	private String description;

}
