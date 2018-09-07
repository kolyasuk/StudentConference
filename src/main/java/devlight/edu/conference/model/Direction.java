package devlight.edu.conference.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Direction {

	@NotNull
	private int id;
	@NotNull
	@Size(min = 2, message = "Direction name must be longer than 2")
	private String directionName;

	public Direction(int id, String directionName) {
		super();
		this.id = id;
		this.directionName = directionName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDirectionName() {
		return directionName;
	}

	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}

	@Override
	public String toString() {
		return "Direction [id=" + id + ", directionName=" + directionName + "]";
	}

}
