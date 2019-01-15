package ua.nure.balagura.SummaryTask4.db.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.balagura.SummaryTask4.consts.Requests;
import ua.nure.balagura.SummaryTask4.db.ConnectionPool;
import ua.nure.balagura.SummaryTask4.db.dao.AutoFacadeDAO;
import ua.nure.balagura.SummaryTask4.db.facade.AutoFacade;
import ua.nure.balagura.SummaryTask4.exception.DBException;

public class AutoFacadeDaoImpl implements AutoFacadeDAO {
	private static final Logger LOG = Logger.getLogger(AutoFacadeDaoImpl.class);

	@Override
	public List<AutoFacade> gettAll() throws DBException {
		LOG.debug("AutoFacadeDaoImpl#getAll");
		List<AutoFacade> autos = new ArrayList<AutoFacade>();
		LOG.trace("Executing query " + Requests.SQL_SELECT_ALL_AUTO_FACADES);
		try (Connection conn = ConnectionPool.getInstance().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(Requests.SQL_SELECT_ALL_AUTO_FACADES)) {
			while (rs.next()) {
				AutoFacade aa = new AutoFacade();
				aa.setId(rs.getInt("id"));
				aa.setName(rs.getString("name"));
				aa.setSeats(rs.getInt("seats"));
				aa.setConditionId(rs.getInt("condition_id"));
				aa.setCondition(rs.getString("condition"));
				autos.add(aa);
			}
			LOG.debug("Rceive " + autos.size() + " autos");
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		return autos;
	}
}