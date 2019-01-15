package ua.nure.balagura.SummaryTask4.web.command.list;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.balagura.SummaryTask4.consts.Paths;
import ua.nure.balagura.SummaryTask4.db.dao.FlightFacadeDAO;
import ua.nure.balagura.SummaryTask4.db.dao.impl.FlightFacadeDaoImpl;
import ua.nure.balagura.SummaryTask4.db.facade.FlightFacade;
import ua.nure.balagura.SummaryTask4.exception.AppException;
import ua.nure.balagura.SummaryTask4.web.command.Command;

public class ListFlightsCommand extends Command {

	private static final long serialVersionUID = 4601421340754092915L;
	private static final Logger LOG = Logger.getLogger(ListFlightsCommand.class);

	private static class CompareByStatus implements Comparator<FlightFacade> {
		@Override
		public int compare(FlightFacade o1, FlightFacade o2) {
			if (o1.getStatusId() > o2.getStatusId()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	private static Comparator<FlightFacade> compareByStatus = new CompareByStatus();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Commands starts");

		HttpSession session = request.getSession();

		FlightFacadeDAO flightsFacadeDao = new FlightFacadeDaoImpl();
		List<FlightFacade> flightFacadeList = flightsFacadeDao.getAll();
		LOG.trace("Found in DB: flightList --> " + flightFacadeList);

		Collections.sort(flightFacadeList, compareByStatus);

		// put user order beans list to request
		session.setAttribute("flightFacadeList", flightFacadeList);
		LOG.trace("Set the session attribute: flightList --> " + flightFacadeList);

		LOG.debug("Commands finished");
		return Paths.PAGE_FLIGHTS;
	}
}
