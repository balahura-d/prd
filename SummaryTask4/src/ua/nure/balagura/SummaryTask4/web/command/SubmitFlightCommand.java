package ua.nure.balagura.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.balagura.SummaryTask4.consts.Paths;
import ua.nure.balagura.SummaryTask4.db.dao.FlightDAO;
import ua.nure.balagura.SummaryTask4.db.dao.FlightFacadeDAO;
import ua.nure.balagura.SummaryTask4.db.dao.impl.FlightDaoImpl;
import ua.nure.balagura.SummaryTask4.db.dao.impl.FlightFacadeDaoImpl;
import ua.nure.balagura.SummaryTask4.db.entity.Condition;
import ua.nure.balagura.SummaryTask4.db.entity.Flight;
import ua.nure.balagura.SummaryTask4.exception.AppException;
import ua.nure.balagura.SummaryTask4.exception.DBException;

public class SubmitFlightCommand extends Command {

	private static final long serialVersionUID = -633074417410864949L;
	private static final Logger LOG = Logger.getLogger(SubmitFlightCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Commands starts");

		int flightId = Integer.parseInt(request.getParameter("flightId"));
		FlightDAO fDao = new FlightDaoImpl();
		Flight flight = fDao.selectById(flightId);
		LOG.trace("Receive flight -->" + flight);
		
		int condId = Integer.parseInt(request.getParameter("condition"));
		Condition condition = Condition.values()[condId];
		LOG.trace("Receive condition -->" + condition);
		
		FlightFacadeDAO ffDao = new FlightFacadeDaoImpl();
		try {
			ffDao.submitFlight(flight, condition);
		}catch (DBException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}
		
		LOG.debug("Commands finished");
		return Paths.COMMAND_LIST_FLIGHTS;
	}

	

}
