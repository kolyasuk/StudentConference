package devlight.edu.conference.model;

import javax.validation.constraints.NotNull;

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
public class Request_rate {

	@NotNull
	private int request_id;
	@NotNull
	private int juri_id;
	@NotNull
	private double mark;

}
