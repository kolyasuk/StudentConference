package devlight.edu.conference.model;

import java.sql.Date;
import java.util.Arrays;

public class Request {

	private int id;
	private String name;
	private String surname;
	private String phone;
	private Date birthdate;
	private byte[][] photo;
	private byte[][] CV;
	private double avarageMark;
	private String email;
	private int direction_id;
	private int curator_id;
	private boolean approved;

	public Request(int id, String name, String surname, String phone, Date birthdate, byte[][] photo, byte[][] cV, double avarageMark, String email, int direction_id, int curator_id, boolean approved) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.birthdate = birthdate;
		this.photo = photo;
		CV = cV;
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
