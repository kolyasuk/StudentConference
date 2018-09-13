package devlight.edu.conference.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {

	@NotNull
	private int id;
	@NotBlank
	@Size(min = 2, message = "Name must be longer than 2")
	private String name;
	@NotBlank
	@Size(min = 2, message = "Surname must be longer than 2")
	private String surname;
	@NotBlank
	private String phone;
	@NotNull
	private Date birthdate;
	@NotNull
	private int photo_id;
	@NotNull(message = "Add cv [ls")
	private int CV_id;
	@NotNull
	private double avarageMark;
	@Email
	private String email;
	@NotNull
	private int direction_id;
	@NotNull
	private int curator_id;
	@NotNull
	private boolean approved;
	@NotNull
	private Set<Application_rate> rate_list = new HashSet<Application_rate>(0);

}
