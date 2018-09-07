package devlight.edu.conference.model;

import java.sql.Date;
import java.util.Arrays;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Request {

	@NotNull
	private int id;
	@NotNull
	@Size(min = 2, message = "Name must be longer than 2")
	private String name;
	@NotNull
	@Size(min = 2, message = "Surname must be longer than 2")
	private String surname;
	@NotNull
	private String phone;
	@NotNull
	private Date birthdate;
	@NotNull
	private byte[][] photo;
	@NotNull(message = "Add cv [ls")
	private byte[][] CV;
	@NotNull
	private double avarageMark;
	@NotNull
	@Size(min = 7, message = "Email must be longer than 7")
	private String email;
	@NotNull
	private int direction_id;
	@NotNull
	private int curator_id;
	@NotNull
	private boolean approved;

	public Request(int id, String name, String surname, String phone, Date birthdate, byte[][] photo, byte[][] cV, double avarageMark, String email, int direction_id, int curator_id, boolean approved) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.birthdate = birthdate;
		this.photo = photo;
		this.CV = cV;
		this.avarageMark = avarageMark;
		this.email = email;
		this.direction_id = direction_id;
		this.curator_id = curator_id;
		this.approved = approved;
	}

	public Request() {
		// TODO Auto-generated constructor stub
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public byte[][] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[][] photo) {
		this.photo = photo;
	}

	public byte[][] getCV() {
		return CV;
	}

	public void setCV(byte[][] cV) {
		CV = cV;
	}

	public double getAvarageMark() {
		return avarageMark;
	}

	public void setAvarageMark(double avarageMark) {
		this.avarageMark = avarageMark;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDirection_id() {
		return direction_id;
	}

	public void setDirection_id(int direction_id) {
		this.direction_id = direction_id;
	}

	public int getCurator_id() {
		return curator_id;
	}

	public void setCurator_id(int curator_id) {
		this.curator_id = curator_id;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "Request [getId()=" + getId() + ", getName()=" + getName() + ", getSurname()=" + getSurname() + ", getPhone()=" + getPhone() + ", getBirthdate()=" + getBirthdate() + ", getPhoto()=" + Arrays.toString(getPhoto()) + ", getCV()="
				+ Arrays.toString(getCV()) + ", getAvarageMark()=" + getAvarageMark() + ", getEmail()=" + getEmail() + ", getDirection_id()=" + getDirection_id() + ", getCurator_id()=" + getCurator_id() + ", isApproved()=" + isApproved() + "]";
	}

}
