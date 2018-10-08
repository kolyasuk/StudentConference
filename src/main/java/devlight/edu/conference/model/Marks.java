package devlight.edu.conference.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	private int request_id;
	@NotNull
	private int juri_id;
	@NotNull
	private double mark;

}
