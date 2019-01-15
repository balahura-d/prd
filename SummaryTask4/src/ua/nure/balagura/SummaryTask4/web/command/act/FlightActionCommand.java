package ua.nure.balagura.SummaryTask4.web.command.act;

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
import ua.nure.balagura.SummaryTask4.db.entity.Flight;
import ua.nure.balagura.SummaryTask4.exception.AppException;
import ua.nure.balagura.SummaryTask4.web.command.Command;

public class FlightActionCommand extends Command {

	private static final long serialVersionUID = -5988776074298705623L;
	private static final Logger LOG = Logger.getLogger(FlightActionCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Commands starts");

		int id = Integer.parseInt(request.getParameter("beanId"));
		FlightDAO flightDao = new FlightDaoImpl();
		Flight flight = new Flight();

		
		if (request.getParameter("delFlight") != null) {
			if (flightDao.deleteById(id)) 
				LOG.trace("Delete from DB: flight with id --> " + id);

		} else if (request.getParameter("regFlight") != null) {
			LOG.trace("Receive from JSP: name --> " + request.getParameter("name"));
			flight = new Flight(request.getParameter("name"));
			
			if (flightDao.create(flight))
				LOG.trace("Insert to DB: flight --> " + flight);

		} else if (request.getParameter("cancelFlight") != null) {
			flight = flightDao.selectById(id);
			FlightFacadeDAO flightFacadeDao = new FlightFacadeDaoImpl();
			flightFacadeDao.cancelFlight(flight);
		} else if (request.getParameter("copyFlight") != null) {
			flight = flightDao.selectById(id);
			Flight newFlight = new Flight(flight.getName());
			flightDao.create(newFlight);
		}

		LOG.debug("Commands finished");

		return Paths.COMMAND_LIST_FLIGHTS;
	}
}