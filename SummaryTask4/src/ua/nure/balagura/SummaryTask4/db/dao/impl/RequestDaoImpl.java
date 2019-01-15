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
import ua.nure.balagura.SummaryTask4.db.dao.RequestDAO;
import ua.nure.balagura.SummaryTask4.db.entity.Request;
import ua.nure.balagura.SummaryTask4.exception.DBException;

public class RequestDaoImpl implements RequestDAO {
	private static final Logger LOG = Logger.getLogger(RequestDaoImpl.class);

	@Override
	public Request selectById(int id) throws DBException {
		LOG.debug("RequestDaoImpl#selectById");
		Request request = new Request();

		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_SELECT_REQUEST_BY_ID)) {
			pstmt.setInt(1, id);
			LOG.debug("Executing query " + Requests.SQL_SELECT_REQUEST_BY_ID + " with id=" + id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			request = getRequest(rs);
			LOG.trace("Having request=" + request);
			rs.close();
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return request;
	}

	@Override
	public List<Request> getAll() throws DBException {
		LOG.debug("RequestDaoImpl#selectAll");
		List<Request> requests = new ArrayList<Request>();

		LOG.trace("Executing query " + Requests.SQL_SELECT_ALL_REQUESTS);
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(Requests.SQL_SELECT_ALL_REQUESTS)) {
			while (rs.next()) {
				requests.add(getRequest(rs));
			}
			LOG.debug("Rceive " + requests.size() + " requests");
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return requests;
	}

	@Override
	public boolean deleteById(int id) throws DBException {
		LOG.debug("RequestDaoImpl#deleteById");
		boolean result = false;
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_DELETE_REQUEST_BY_ID)) {
			pstmt.setInt(1, id);
			LOG.debug("Executing update " + Requests.SQL_DELETE_REQUEST_BY_ID + " with id=" + id);
			if (pstmt.executeUpdate() > 0) {
				conn.commit();
				result = true;
			}
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return result;
	}

	@Override
	public boolean create(Request request) throws DBException {
		LOG.debug("RequestDaoImpl#create");
		boolean result = false;
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_INSERT_REQUEST,
						Statement.RETURN_GENERATED_KEYS);) {
			int index = 1;
			pstmt.setInt(index++, request.getDriverId());
			pstmt.setInt(index++, request.getFlightId());
			pstmt.setInt(index++, request.getSeats());
			pstmt.setInt(index++, request.getStatusId());
			pstmt.setInt(index++, request.getProcessedBy());
			LOG.trace("Executing update " + Requests.SQL_INSERT_REQUEST + " with request=" + request);
			if (pstmt.executeUpdate() > 0) {
				result = true;
				ResultSet rs = null;
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					request.setId(rs.getInt(1));
					LOG.debug("Receive id=" + request.getId());
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
	public boolean update(Request request) throws DBException {
		LOG.debug("RequestDaoImpl#update");
		boolean result = false;
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_UPDATE_REQUEST)) {
			int index = 1;
			pstmt.setInt(index++, request.getDriverId());
			pstmt.setInt(index++, request.getFlightId());
			pstmt.setInt(index++, request.getSeats());
			pstmt.setInt(index++, request.getStatusId());
			pstmt.setInt(index++, request.getProcessedBy());
			pstmt.setInt(index++, request.getId());
			LOG.trace("Executing update " + Requests.SQL_UPDATE_REQUEST + " with id=" + request.getId());
			if (pstmt.executeUpdate() > 0) {
				conn.commit();
				result = true;
			}
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return result;
	}

	private Request getRequest(ResultSet rs) throws SQLException {
		Request request = new Request();
		request.setId(rs.getInt("id"));
		request.setDriverId(rs.getInt("driver_id"));
		request.setFlightId(rs.getInt("flight_id"));
		request.setSeats(rs.getInt("seats"));
		request.setStatusId(rs.getInt("status_id"));
		request.setProcessedBy(rs.getInt("processed_by"));
		return request;
	}

	@Override
	public boolean update(Request req, Connection conn) throws DBException {
		boolean result = false;
		try (PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_UPDATE_REQUEST)) {
			int index = 1;
			pstmt.setInt(index++, req.getDriverId());
			pstmt.setInt(index++, req.getFlightId());
			pstmt.setInt(index++, req.getSeats());
			pstmt.setInt(index++, req.getStatusId());
			pstmt.setInt(index++, req.getProcessedBy());
			pstmt.setInt(index++, req.getId());
			LOG.trace("Executing update " + Requests.SQL_UPDATE_REQUEST + " with id=" + req.getId());
			if (pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return result;
	}
}
