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
	@NotNull(message = "{marks.juryId.notNull}")
	@Column(name = "jury_id")
	private int juryId;
	@NotNull(message = "{marks.applicationId.notNull}")
	@Column(name = "application_id")
	private int applicationId;
	@NotNull
	@Min(value = 0, message = "{marks.mark.size.min}")
	@Max(value = 10, message = "{marks.mark.size.max}")
	private double mark;

}
