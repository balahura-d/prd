package ua.nure.balagura.practice8.db.entity;

public class Group {
	private int id;
	private String name;

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

	public static Group createGroup(String string) {
		Group group = new Group();
		group.name = string;
		return group;
	}
	@Override
	public String toString() {
		return "Group [Name=" + name + "]";
	}

}
