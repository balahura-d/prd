package ua.nure.balagura.practice8.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.balagura.practice8.db.entity.Group;
import ua.nure.balagura.practice8.db.entity.User;

public class DBManager {

	private static final String SQL_INSERT_USER = "insert into users values (default, ?)";
	private static final String SQL_SELECT_ALL_USERS = "select * from users";
	public static final String SQL_SELECT_USER = "select * from users where login = ?";

	private static final String SQL_INSERT_GROUP = "insert into groups values (default, ?)";
	private static final String SQL_SELECT_ALL_GROUPS = "select * from groups";
	public static final String SQL_SELECT_GROUP = "select * from groups where name = ?";

	private static final String SQL_INSERT_GROUP_TO_USER = "insert into users_groups values (?, ?)";
	private static final String SQL_SELECT_GROUPS_TO_USER = "select g.* from groups g join users_groups ug on g.id = ug.group_id where ug.user_id = ?;";

	private static DBManager instance; // == null

	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		Connection con = DriverManager
				.getConnection("jdbc:mysql://192.168.65.43:3307/user2db?user=user2&password=user2pass");

		return con;
	}

	public boolean insertUser(User user) throws SQLException {

		boolean res = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con = getConnection();

		pstmt = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, user.getLogin());

		if (pstmt.executeUpdate() > 0) {
			res = true;
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
			}
		}
		rs.close();
		pstmt.close();
		con.close();

		return res;
	}

	public List<User> findAllUsers() throws SQLException {

		List<User> users = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		con = getConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery(SQL_SELECT_ALL_USERS);

		while (rs.next()) {
			users.add(extractUser(rs));
		}
		rs.close();
		stmt.close();
		con.close();

		return users;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		return user;
	}

	public boolean insertGroup(Group group) throws SQLException {

		boolean res = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con = getConnection();

		pstmt = con.prepareStatement(SQL_INSERT_GROUP, Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, group.getName());

		if (pstmt.executeUpdate() > 0) {
			res = true;
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				group.setId(rs.getInt(1));
			}
		}
		rs.close();
		pstmt.close();
		con.close();

		return res;
	}

	public List<Group> findAllGroups() throws SQLException {

		List<Group> groups = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		con = getConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery(SQL_SELECT_ALL_GROUPS);

		while (rs.next()) {
			groups.add(extractGroup(rs));
		}
		rs.close();
		stmt.close();
		con.close();

		return groups;
	}

	private Group extractGroup(ResultSet rs) throws SQLException {
		Group groups = new Group();
		groups.setId(rs.getInt("id"));
		groups.setName(rs.getString("name"));
		return groups;
	}

	public User getUser(String string) throws SQLException {
		User user = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = getConnection();

		pstmt = con.prepareStatement(SQL_SELECT_USER);
		pstmt.setString(1, string);

		rs = pstmt.executeQuery();

		if (rs.next()) {
			user = extractUser(rs);
		}
		rs.close();
		pstmt.close();
		con.close();

		return user;
	}

	public Group getGroup(String string) throws SQLException {
		Group group = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = getConnection();

		pstmt = con.prepareStatement(SQL_SELECT_GROUP);
		pstmt.setString(1, string);

		rs = pstmt.executeQuery();

		if (rs.next()) {
			group = extractGroup(rs);
		}
		rs.close();
		pstmt.close();
		con.close();

		return group;
	}

	public void setGroupsForUser(User user, Group... groups) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			pstmt = con.prepareStatement(SQL_INSERT_GROUP_TO_USER);
			pstmt.setInt(1, user.getId());
			
			for (int i = 0; i < groups.length; i++) {
				Group group = groups[i];
				pstmt.setInt(2, group.getId());
				pstmt.executeUpdate();
			}
			con.commit();
		} catch (SQLException ex) {
			con.rollback();
		} finally {
			con.close();
		}

		return;
	}

	public List<Group> getUserGroups(User user) throws SQLException {
		List<Group> groups = new ArrayList<>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = getConnection();
		pstmt = con.prepareStatement(SQL_SELECT_GROUPS_TO_USER);
		pstmt.setInt(1, user.getId());
		rs = pstmt.executeQuery();

		while (rs.next()) {
			groups.add(extractGroup(rs));
		}
		rs.close();
		pstmt.close();
		con.close();

		return groups;
	}

	public boolean deleteGroup(Group group) {
		
		
		
		return false;
		// TODO Auto-generated method stub
	}

}
