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
import ua.nure.balagura.SummaryTask4.db.dao.AutoDAO;
import ua.nure.balagura.SummaryTask4.db.entity.Auto;
import ua.nure.balagura.SummaryTask4.exception.DBException;

public class AutoDaoImpl implements AutoDAO {
	private static final Logger LOG = Logger.getLogger(AutoDaoImpl.class);

	@Override
	public Auto selectById(int id) throws DBException {
		LOG.debug("AutoDaoImpl#selectById");
		Auto auto = new Auto();

		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_SELECT_AUTO_BY_ID)) {
			pstmt.setInt(1, id);
			LOG.debug("Executing query " + Requests.SQL_SELECT_AUTO_BY_ID + " with id=" + id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			auto = getAuto(rs);
			LOG.trace("Having auto=" + auto);
			rs.close();
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return auto;
	}

	@Override
	public List<Auto> getAll() throws DBException {
		LOG.debug("AutoDaoImpl#selectAll");
		List<Auto> autos = new ArrayList<Auto>();

		LOG.trace("Executing query " + Requests.SQL_SELECT_ALL_AUTOS);
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(Requests.SQL_SELECT_ALL_AUTOS)) {
			while (rs.next()) {
				autos.add(getAuto(rs));
			}
			LOG.debug("Rceive " + autos.size() + " autos");
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return autos;
	}

	@Override
	public boolean deleteById(int id) throws DBException {
		LOG.debug("AutoDaoImpl#deleteById");
		boolean result = false;
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_DELETE_AUTO_BY_ID)) {
			pstmt.setInt(1, id);
			LOG.debug("Executing update " + Requests.SQL_DELETE_AUTO_BY_ID + " with id=" + id);
			if (pstmt.executeUpdate() > 0) {
				result = true;
				conn.commit();
			}
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return result;
	}

	@Override
	public boolean create(Auto auto) throws DBException {
		LOG.debug("AutoDaoImpl#create");
		boolean result = false;
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_INSERT_AUTO,
						Statement.RETURN_GENERATED_KEYS);) {
			int index = 1;
			pstmt.setString(index++, auto.getName());
			pstmt.setInt(index++, auto.getSeats());
			pstmt.setInt(index++, auto.getConditionId());
			LOG.trace("Executing update " + Requests.SQL_INSERT_AUTO + " with auto=" + auto);
			if (pstmt.executeUpdate() > 0) {
				result = true;
				ResultSet rs = null;
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					auto.setId(rs.getInt(1));
					LOG.debug("Receive id=" + auto.getId());
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
	public boolean update(Auto auto) throws DBException {
		LOG.debug("AutoDaoImpl#update");
		boolean result = false;
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_UPDATE_AUTO)) {
			int index = 1;
			pstmt.setString(index++, auto.getName());
			pstmt.setInt(index++, auto.getSeats());
			pstmt.setInt(index++, auto.getConditionId());
			pstmt.setInt(index++, auto.getId());
			LOG.trace("Executing update " + Requests.SQL_UPDATE_AUTO + " with id=" + auto.getId());
			if (pstmt.executeUpdate() > 0) {
				conn.commit();
				result = true;
			}
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return result;
	}

	private Auto getAuto(ResultSet rs) throws SQLException {
		Auto auto = new Auto();
		auto.setId(rs.getInt("id"));
		auto.setName(rs.getString("name"));
		auto.setSeats(rs.getInt("seats"));
		auto.setConditionId(rs.getInt("condition_id"));
		return auto;
	}

	@Override
	public boolean update(Auto auto, Connection conn) throws DBException {
		boolean result = false;
		try (PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_UPDATE_AUTO)) {
			int index = 1;
			pstmt.setString(index++, auto.getName());
			pstmt.setInt(index++, auto.getSeats());
			pstmt.setInt(index++, auto.getConditionId());
			pstmt.setInt(index++, auto.getId());
			LOG.trace("Executing update " + Requests.SQL_UPDATE_AUTO + " with id=" + auto.getId());
			if (pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return result;
	}

}
