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
import ua.nure.balagura.SummaryTask4.db.dao.FlightDAO;
import ua.nure.balagura.SummaryTask4.db.entity.Flight;
import ua.nure.balagura.SummaryTask4.exception.DBException;

public class FlightDaoImpl implements FlightDAO {
	private static final Logger LOG = Logger.getLogger(FlightDaoImpl.class);

	@Override
	public Flight selectById(int id) throws DBException {
		LOG.debug("FlightDaoImpl#selectById");
		Flight flight = new Flight();

		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_SELECT_FLIGHT_BY_ID)) {
			pstmt.setInt(1, id);
			LOG.debug("Executing query " + Requests.SQL_SELECT_FLIGHT_BY_ID + " with id=" + id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			flight = getFlight(rs);
			LOG.trace("Having flight=" + flight);
			rs.close();
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return flight;
	}

	@Override
	public List<Flight> getAll() throws DBException {
		LOG.debug("FlightDaoImpl#selectAll");
		List<Flight> flights = new ArrayList<Flight>();

		LOG.trace("Executing query " + Requests.SQL_SELECT_ALL_FLIGHTS);
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(Requests.SQL_SELECT_ALL_FLIGHTS)) {
			while (rs.next()) {
				flights.add(getFlight(rs));
			}
			LOG.debug("Rceive " + flights.size() + " flights");
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return flights;
	}

	@Override
	public boolean deleteById(int id) throws DBException {
		LOG.debug("FlightDaoImpl#deleteById");
		boolean result = false;
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_DELETE_FLIGHT_BY_ID)) {
			pstmt.setInt(1, id);
			LOG.debug("Executing update " + Requests.SQL_DELETE_FLIGHT_BY_ID + " with id=" + id);
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
	public boolean create(Flight flight) throws DBException {
		LOG.debug("FlightDaoImpl#create");
		boolean result = false;
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_INSERT_FLIGHT,
						Statement.RETURN_GENERATED_KEYS);) {
			int index = 1;
			pstmt.setString(index++, flight.getName());
			pstmt.setDate(index++, flight.getDate());
			pstmt.setInt(index++, flight.getStatusId());
			LOG.trace("Executing update " + Requests.SQL_INSERT_FLIGHT + " with flight=" + flight);
			if (pstmt.executeUpdate() > 0) {
				result = true;
				ResultSet rs = null;
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					flight.setId(rs.getInt(1));
					LOG.debug("Receive id=" + flight.getId());
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
	public boolean update(Flight flight) throws DBException {
		LOG.debug("FlightDaoImpl#update");
		boolean result = false;
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_UPDATE_FLIGHT)) {
			int index = 1;
			pstmt.setString(index++, flight.getName());
			pstmt.setDate(index++, flight.getDate());
			pstmt.setInt(index++, flight.getAutoId());
			pstmt.setInt(index++, flight.getDriverId());
			pstmt.setInt(index++, flight.getStatusId());
			pstmt.setInt(index++, flight.getId());
			LOG.trace("Executing update " + Requests.SQL_UPDATE_FLIGHT + " with id=" + flight.getId());
			if (pstmt.executeUpdate() > 0) {
				conn.commit();
				result = true;
			}
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return result;
	}

	public boolean createFlight(String name) throws DBException {
		LOG.debug("FlightDaoImpl#createFlight");

		return create(new Flight(name));
	}

	private Flight getFlight(ResultSet rs) throws SQLException {
		Flight flight = new Flight();
		flight.setId(rs.getInt("id"));
		flight.setName(rs.getString("name"));
		flight.setDate(rs.getDate("create_date"));
		flight.setStatusId(rs.getInt("status_id"));
		flight.setAutoId(rs.getInt("auto_id"));
		flight.setDriverId(rs.getInt("driver_id"));
		return flight;
	}

	@Override
	public boolean update(Flight flight, Connection conn) throws DBException {
		boolean result = false;
		try (PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_UPDATE_FLIGHT)) {
			int index = 1;
			pstmt.setString(index++, flight.getName());
			pstmt.setDate(index++, flight.getDate());
			pstmt.setInt(index++, flight.getAutoId());
			pstmt.setInt(index++, flight.getDriverId());
			pstmt.setInt(index++, flight.getStatusId());
			pstmt.setInt(index++, flight.getId());
			LOG.trace("Executing update " + Requests.SQL_UPDATE_FLIGHT + " with id=" + flight.getId());
			if (pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return result;
	}

}
