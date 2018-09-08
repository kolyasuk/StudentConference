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
public class User {

	@NotNull
	private int id;
	@NotNull
	@Size(min = 5, message = "Login must be longer then 5")
	private String login;
	@NotNull
	@Size(min = 5, message = "Password must be longer then 5")
	private String password;
	@NotNull
	private String role;

}
