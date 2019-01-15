package ua.nure.balagura.SummaryTask4.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.balagura.SummaryTask4.consts.Requests;
import ua.nure.balagura.SummaryTask4.db.ConnectionPool;
import ua.nure.balagura.SummaryTask4.db.dao.UserDAO;
import ua.nure.balagura.SummaryTask4.db.entity.Role;
import ua.nure.balagura.SummaryTask4.db.entity.User;
import ua.nure.balagura.SummaryTask4.exception.DBException;

public class UserDaoImpl implements UserDAO {
	private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

	@Override
	public User selectById(int id) throws DBException {
		LOG.debug("UserDaoImpl#selectById");
		User user = new User();

		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_SELECT_USER_BY_ID)) {
			pstmt.setInt(1, id);
			LOG.debug("Executing query " + Requests.SQL_SELECT_USER_BY_ID + " with id=" + id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			user = getUser(rs);
			LOG.trace("Having user=" + user);
			rs.close();
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return user;
	}

	@Override
	public List<User> getAll() throws DBException {
		LOG.debug("UserDaoImpl#selectAll");
		List<User> users = new ArrayList<User>();

		LOG.trace("Executing query " + Requests.SQL_SELECT_ALL_USERS);
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(Requests.SQL_SELECT_ALL_USERS)) {
			while (rs.next()) {
				users.add(getUser(rs));
			}
			LOG.debug("Rceive " + users.size() + " autos");
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return users;
	}

	@Override
	public boolean deleteById(int id) throws DBException {
		LOG.debug("UserDaoImpl#deleteById");
		boolean result = false;
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_DELETE_USER_BY_ID)) {
			pstmt.setInt(1, id);
			LOG.trace("Executing update " + Requests.SQL_DELETE_USER_BY_ID + " with id=" + id);
			if (pstmt.executeUpdate() > 0) {
				conn.commit();
				LOG.debug("User with id"+id+"was deleted.");
				result = true;
			}
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return result;
	}

	@Override
	public boolean create(User user) throws DBException {
		LOG.debug("UserDaoImpl#create");
		boolean result = false;
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_INSERT_USER,
						Statement.RETURN_GENERATED_KEYS);) {
			int index = 1;
			pstmt.setString(index++, user.getLogin());
			pstmt.setString(index++, user.getPassword());
			pstmt.setString(index++, user.getFullName());
			pstmt.setInt(index++, user.getRoleId());
			LOG.trace("Executing update " + Requests.SQL_INSERT_USER + " with user=" + user);
			if (pstmt.executeUpdate() > 0) {
				result = true;
				ResultSet rs = null;
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					user.setId(rs.getInt(1));
					LOG.debug("Receive id=" + user.getId());
				}
				conn.commit();
				rs.close();
			}
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return result;
	}

	@Override
	public boolean update(User user) throws DBException {
		LOG.debug("UserDaoImpl#update");
		boolean result = false;
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_UPDATE_USER)) {
			int index = 1;
			pstmt.setString(index++, user.getLogin());
			pstmt.setString(index++, user.getPassword());
			pstmt.setString(index++, user.getFullName());
			pstmt.setInt(index++, user.getRoleId());
			pstmt.setInt(index++, user.getId());
			LOG.trace("Executing update " + Requests.SQL_UPDATE_USER + " with id=" + user.getId());
			if (pstmt.executeUpdate() > 0) {
				conn.commit();
				result = true; }
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return result;
	}

	@Override
	public User selectUserByLogin(String login) throws DBException {
		LOG.debug("UserDaoImpl#selectUserByLogin");
		User user = new User();

		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_SELECT_USER_BY_LOGIN)) {
			pstmt.setString(1, login);
			LOG.debug("Executing query " + Requests.SQL_SELECT_USER_BY_LOGIN + " with login=" + login);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			user = getUser(rs);
			LOG.trace("Having user=" + user);
			rs.close();
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return user;
	}

	@Override
	public List<User> selectUsersByRole(int roleId) throws DBException {
		LOG.debug("UserDaoImpl#selectUsersByRole(int)");
		List<User> users = new ArrayList<User>();

		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_SELECT_USERS_BY_ROLE_ID)) {
			pstmt.setInt(1, roleId);
			LOG.debug("Executing query " + Requests.SQL_SELECT_USERS_BY_ROLE_ID + " with role_id=" + roleId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				users.add(getUser(rs));
			}
			rs.close();
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return users;
	}

	@Override
	public List<User> selectUsersByRole(Role role) throws DBException {
		LOG.debug("UserDaoImpl#selectUsersByRole(role)");
		return selectUsersByRole(role.ordinal());
	}

	private User getUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setFullName(rs.getString("full_name"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setRoleId(rs.getInt("role_id"));
		return user;
	}

}
