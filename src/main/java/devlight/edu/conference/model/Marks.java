package devlight.edu.conference.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Marks {

	@Id
	@GeneratedValue
	private int id;
	@NotNull
	@Column(name = "jury_id")
	private int juryId;
	@NotNull
	@Column(name = "application_id")
	private int applicationId;
	@NotNull
	@Min(value = 0, message = "Mark can not be less than {value}")
	@Max(value = 10, message = "Mark can not be bigger than {value}")
	private double mark;

}
