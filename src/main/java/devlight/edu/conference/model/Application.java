package devlight.edu.conference.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Application implements Serializable {

	private static final long serialVersionUID = -3084384466548213690L;
	@Id
	@GeneratedValue
	private int id;
	@NotBlank
	@Size(min = 2, message = "Name must be longer than {min}")
	private String name;
	@NotBlank
	@Size(min = 2, message = "Surname must be longer than {min}")
	private String surname;
	@NotBlank
	@Size(min = 10, message = "Phone number size  must be longer than {min}")
	private String phone;
	@NotNull
	private Date birthdate;
	@NotNull(message = "Add photo pls")
	private int photo_id;
	@NotNull(message = "Add CV pls")
	private int cv_id;
	@NotNull
	@Min(value = 0, message = "Mark can not be less than {value}")
	@Max(value = 10, message = "Mark can not be biger than {value}")
	private double avarage_mark;
	@Email(message = "Use email format like this: \"example@gmail.com\" ")
	private String email;
	@Min(value = 0, message = "Direction cannot be less than {value}")
	@NotNull(message = "Cannot be null")
	private int direction_id;
	@Min(value = 0, message = "Curator cannot be less than {value}")
	@NotNull(message = "Cannot be null")
	private int curator_id;
	private boolean approved;
	private boolean revised;

}
