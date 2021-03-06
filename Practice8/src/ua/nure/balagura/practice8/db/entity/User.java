package ua.nure.balagura.practice8.db.entity;

public class User {
	private int id;
	private String login;

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

	public static User createUser(String string) {
		User user = new User();
		user.login = string;
		return user;
	}

	@Override
	public String toString() {
		return "User [login=" + login + "]";
	}
}
