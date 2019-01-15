package ua.nure.balagura.SummaryTask4.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.balagura.SummaryTask4.consts.Requests;
import ua.nure.balagura.SummaryTask4.db.ConnectionPool;
import ua.nure.balagura.SummaryTask4.db.dao.FlightFacadeDAO;
import ua.nure.balagura.SummaryTask4.db.entity.Condition;
import ua.nure.balagura.SummaryTask4.db.entity.Flight;
import ua.nure.balagura.SummaryTask4.db.facade.FlightFacade;
import ua.nure.balagura.SummaryTask4.exception.DBException;

public class FlightFacadeDaoImpl implements FlightFacadeDAO {
	private static final Logger LOG = Logger.getLogger(FlightFacadeDaoImpl.class);

	@Override
	public List<FlightFacade> getAll() throws DBException {
		LOG.debug("FlightFacadeDaoImpl#getAll");
		List<FlightFacade> flights = new ArrayList<FlightFacade>();

		LOG.trace("Executing query " + Requests.SQL_SELECT_ALL_FLIGHT_FACADES);
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(Requests.SQL_SELECT_ALL_FLIGHT_FACADES)) {
			while (rs.next()) {
				FlightFacade ff = new FlightFacade();
				ff.setId(rs.getInt("id"));
				ff.setName(rs.getString("name"));
				ff.setDate(rs.getDate("create_date"));
				ff.setStatusId(rs.getInt("status_id"));
				ff.setStatusName(rs.getString("status"));
				ff.setAutoName(rs.getString("auto"));
				ff.setDriverName(rs.getString("driver"));
				ff.setDriverId(rs.getInt("driver_id"));
				flights.add(ff);
			}
			LOG.debug("Rceive " + flights.size() + " flights");
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return flights;
	}

	@Override
	public boolean submitFlight(Flight flight, Condition condition) throws DBException {
		LOG.debug("FlightFacadeDaoImpl#submitFlight");
		int conditionId = condition.ordinal();

		boolean result = false;
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_UPDATE_FLIGHT_AUTO_AFTER_SUBMIT)) {
			pstmt.setInt(1, conditionId);
			pstmt.setInt(2, flight.getId());
			LOG.trace("Executing update SQL_UPDATE_FLIGHT_AUTO_AFTER_SUBMIT with condId=" + conditionId
					+ " and flightId=" + flight.getId());
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
	public boolean cancelFlight(Flight flight) throws DBException {
		LOG.debug("FlightFacadeDaoImpl#cancelFlight");
		boolean result = false;
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_UPDATE_FLIGHT_AUTO_AFTER_CANCEL)) {

			int max = Condition.TO_WRITE_OFF.ordinal();
			int min = Condition.WAS_IN_USE.ordinal();
			max -= min;
			int rndConditionId = (int) (Math.random() * ++max) + min;
			LOG.trace("Executing update SQL_UPDATE_FLIGHT_AUTO_AFTER_CANCEL with condId=" + rndConditionId
					+ " and flightId=" + flight.getId());
			pstmt.setInt(1, rndConditionId);
			pstmt.setInt(2, flight.getId());
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
	public int[] getStat(Date from, Date to) throws DBException {
		int[] arr = new int[4];
		
		java.sql.Date _from = new java.sql.Date(from.getTime());
		java.sql.Date _to = new java.sql.Date(to.getTime());
		
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_SELECT_STAT_BETWEEN_DATE)) {
			pstmt.setDate(1, _from);
			pstmt.setDate(2, _to);
			for (int i = 0; i < arr.length; i++) {
				pstmt.setInt(3, i);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					arr[i] = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}

		// TODO Auto-generated method stub
		return arr;
	}

}
