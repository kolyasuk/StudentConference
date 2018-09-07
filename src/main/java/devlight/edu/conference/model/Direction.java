package devlight.edu.conference.model;

public class Direction {

	private int id;
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
