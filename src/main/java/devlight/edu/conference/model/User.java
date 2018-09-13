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
public class User {

	@NotNull
	private int id;
	@NotBlank
	@Size(min = 5, message = "Login must be longer then 5")
	private String login;
	@NotBlank
	@Size(min = 5, message = "Password must be longer then 5")
	private String password;
	@NotBlank
	private String role;

}
