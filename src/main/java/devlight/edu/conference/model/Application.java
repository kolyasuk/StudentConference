package devlight.edu.conference.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = -3084384466548213690L;
	@Id
	@GeneratedValue
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
	@NotNull(message = "Add cv pls")
	private int CV_id;
	@NotNull
	private double avarage_mark;
	@Email
	private String email;
	@NotNull
	private int direction_id;
	// @ManyToOne(fetch = FetchType.EAGER)
	// @JoinColumn(name = "direction_id")
	// private Direction direction;
	@NotNull
	private int curator_id;
	@NotNull
	private int author_id;
	// @ManyToOne(fetch = FetchType.EAGER)
	// @JoinColumn(name = "curator_id")
	// private Curator curator;
	@NotNull
	private boolean approved;

}
