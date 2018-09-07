package devlight.edu.conference.model;

public class Curator {

	private int id;
	private String name;
	private String surname;
	private byte[][] photo;
	private String description;

	private boolean photoAvailability = photo.length > 0 ? true : false;

	public Curator(int id, String name, String surname, byte[][] photo, String description) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.photo = photo;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public byte[][] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[][] photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Curator [id=" + id + ", name=" + name + ", surname=" + surname + ", photo=" + photoAvailability + ", description=" + description + "]";
	}

}
