package devlight.edu.conference.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Curator {

	@NotNull
	private int id;
	@NotNull
	@Size(min = 2, message = "Name must be longer than 2")
	private String name;
	@NotNull
	@Size(min = 2, message = "Surname must be longer than 2")
	private String surname;
	@NotNull
	private byte[][] photo;
	@NotNull
	@Size(min = 10, message = "Description must be longer than 10")
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
