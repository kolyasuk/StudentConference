package devlight.edu.conference.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Curator {

	@NotNull
	private int id;
	@NotNull
	@Size(min = 2, message = "Name must be longer than 2")
	private String name;
	@NotNull
	@Size(min = 2, message = "Surname must be longer than 2")
	private String surname;
	@NotNull
	private byte[][] photo;
	@NotNull
	@Size(min = 10, message = "Description must be longer than 10")
	private String description;

}
