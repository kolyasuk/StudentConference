package devlight.edu.conference.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

	@NotNull
	private int id;
	@NotNull
	@Size(min = 5, message = "Login must be longer then 5")
	private String login;
	@NotNull
	@Size(min = 5, message = "Password must be longer then 5")
	private String password;
	@NotNull
	private String role;

	public User(int id, String login, String password, String role) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", role=" + role + "]";
	}

}
