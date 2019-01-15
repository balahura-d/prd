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
import ua.nure.balagura.SummaryTask4.db.dao.RequestFacadeDAO;
import ua.nure.balagura.SummaryTask4.db.entity.Auto;
import ua.nure.balagura.SummaryTask4.db.entity.Condition;
import ua.nure.balagura.SummaryTask4.db.entity.Request;
import ua.nure.balagura.SummaryTask4.db.entity.Status;
import ua.nure.balagura.SummaryTask4.db.entity.User;
import ua.nure.balagura.SummaryTask4.db.facade.RequestFacade;
import ua.nure.balagura.SummaryTask4.exception.DBException;

public class RequestFacadeDaoImpl implements RequestFacadeDAO {
	private static final Logger LOG = Logger.getLogger(RequestFacadeDaoImpl.class);

	@Override
	public List<RequestFacade> getAll() throws DBException {
		LOG.debug("RequestFacadeDaoImpl#getAll");

		List<RequestFacade> requests = new ArrayList<RequestFacade>();

		LOG.trace("Executing query " + Requests.SQL_SELECT_ALL_REQUEST_FACADES);
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(Requests.SQL_SELECT_ALL_REQUEST_FACADES)) {
			while (rs.next()) {
				RequestFacade rf = new RequestFacade();
				rf.setId(rs.getInt("id"));
				rf.setDriverId(rs.getInt("driver_id"));
				rf.setDriverName(rs.getString("driver"));
				rf.setFlightId(rs.getInt("flight_id"));
				rf.setFlightName(rs.getString("flight"));
				rf.setSeats(rs.getInt("seats"));
				rf.setStatusName(rs.getString("status"));
				rf.setProcessedByDisp(rs.getString("processed"));
				requests.add(rf);
			}
			LOG.debug("Rceive " + requests.size() + " requests");
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return requests;
	}

	@Override
	public boolean applyRequest(Request req, User processed, Auto auto) throws DBException {

		LOG.debug("RequestFacadeDaoImpl#applyRequest");
		boolean result = false;

		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_UPDATE_REQUEST_FLIGHT_AUTO)) {
			int index = 1;
			pstmt.setInt(index++, Status.PROGRESS.ordinal());
			pstmt.setInt(index++, processed.getId());
			pstmt.setInt(index++, Condition.IS_USED.ordinal());
			pstmt.setInt(index++, auto.getId());
			pstmt.setInt(index++, req.getDriverId());
			pstmt.setInt(index++, Status.PROGRESS.ordinal());
			pstmt.setInt(index++, auto.getId());
			pstmt.setInt(index++, req.getId());
			LOG.trace("Executing update " + Requests.SQL_UPDATE_REQUEST_FLIGHT_AUTO);
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
	public void addAvailableAuto(RequestFacade requestFacade) throws DBException {
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Requests.SQL_SELECT_AUTO_FOR_REQUEST)){
			pstmt.setInt(1, requestFacade.getSeats());	
			ResultSet rs = pstmt.executeQuery();
			List<Auto> availableAuto = new ArrayList<Auto>();
			while(rs.next()) {
				Auto auto = new Auto();
				auto.setId(rs.getInt("id"));
				auto.setName(rs.getString("name"));
				auto.setSeats(rs.getInt("seats"));
				auto.setConditionId(rs.getInt("condition_id"));
				availableAuto.add(auto);
			}
			requestFacade.setAvailableAuto(availableAuto);
			return;
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
	}
}