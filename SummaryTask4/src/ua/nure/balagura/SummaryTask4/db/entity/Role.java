package ua.nure.balagura.SummaryTask4.db.entity;

public enum Role {
	ADMIN, DISPATCHER, DRIVER;

	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}

	public String getName() {
		return name().toLowerCase();
	}
}
