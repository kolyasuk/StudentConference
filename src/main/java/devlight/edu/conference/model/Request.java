package devlight.edu.conference.model;

import java.sql.Date;

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
public class Request {

	@NotNull
	private int id;
	@NotNull
	@Size(min = 2, message = "Name must be longer than 2")
	private String name;
	@NotNull
	@Size(min = 2, message = "Surname must be longer than 2")
	private String surname;
	@NotNull
	private String phone;
	@NotNull
	private Date birthdate;
	@NotNull
	private byte[][] photo;
	@NotNull(message = "Add cv [ls")
	private byte[][] CV;
	@NotNull
	private double avarageMark;
	@NotNull
	@Size(min = 7, message = "Email must be longer than 7")
	private String email;
	@NotNull
	private int direction_id;
	@NotNull
	private int curator_id;
	@NotNull
	private boolean approved;

}
