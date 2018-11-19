package devlight.edu.conference.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	@Id
	@GeneratedValue
	private int id;
	@NotBlank(message = "{role.role.notNull}")
	private String role;

}
