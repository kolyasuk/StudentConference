package devlight.edu.conference.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application_rate {

	@NotNull
	private int request_id;
	@NotNull
	private int juri_id;
	@NotNull
	private double mark;

}
