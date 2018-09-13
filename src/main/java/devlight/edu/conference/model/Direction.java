package devlight.edu.conference.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direction {

	@NotNull
	private int id;
	@NotBlank
	@Size(min = 2, message = "Direction name must be longer than 2")
	private String directionName;

}
