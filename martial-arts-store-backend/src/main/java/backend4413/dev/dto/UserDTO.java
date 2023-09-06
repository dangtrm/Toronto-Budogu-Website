package backend4413.dev.dto;

import backend4413.dev.model.Role;

public class UserDTO {

	private String name;
	private String username;
	private String email;
	private String password;
	private Role role;

	public UserDTO() {
	}

	/**
	 * For registration purposes
	 * 
	 * @param name
	 * @param username
	 * @param email
	 * @param password
	 */
	public UserDTO(String name, String username, String email, String password, Role role) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public UserDTO(String name, String username, String email, String password) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	/**
	 * For login purposes
	 * 
	 * @param username
	 * @param password
	 */
	public UserDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
