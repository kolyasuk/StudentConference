package devlight.edu.conference.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
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
	@Size(min = 2, message = "{application.name.size.min}")
	private String name;

	@NotBlank
	@Size(min = 2, message = "{application.surname.size.min}")
	private String surname;

	@NotBlank
	@Size(min = 10, message = "{application.phone.size.min}")
	private String phone;

	@NotNull(message = "{NotNull.application}")
	private Date birthdate;

	@NotNull(message = "{NotNull.application}")
	private int photo_id;

	@NotNull(message = "{NotNull.application}")
	private int cv_id;

	@Email(message = "{application.email.format}")
	private String email;

	@Min(value = 0, message = "{application.direction_id.size.min}")
	@NotNull(message = "{NotNull.application}")
	private int direction_id;

	@Min(value = 0, message = "{application.curator_id.size.min}")
	@NotNull(message = "{NotNull.application}")
	private int curator_id;

	private double avarage_mark;
	private boolean approved;
	private boolean revised;

}
