package devlight.edu.conference.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@Size(min = 2, max = 15, message = "{curator.name.size.min}")
	private String name;
	@NotBlank
	@Size(min = 2, max = 15, message = "{curator.surname.size.min}")
	private String surname;
	@NotNull(message = "{curator.photo_id.notNull}")
	private int photo_id;
	@NotBlank
	@Size(min = 10, message = "{curator.surname.size.min}")
	private String description;
	// @OneToMany(mappedBy = "curator", cascade = CascadeType.ALL, fetch =
	// FetchType.LAZY)
	// private Set<Application> application;

}
