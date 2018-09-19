package devlight.edu.conference.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User {

	@Id
	@GeneratedValue
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
