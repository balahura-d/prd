package ua.nure.balagura.SummaryTask4.web.command.act;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.balagura.SummaryTask4.consts.Paths;
import ua.nure.balagura.SummaryTask4.db.dao.RequestDAO;
import ua.nure.balagura.SummaryTask4.db.dao.impl.RequestDaoImpl;
import ua.nure.balagura.SummaryTask4.db.entity.Request;
import ua.nure.balagura.SummaryTask4.db.entity.Status;
import ua.nure.balagura.SummaryTask4.db.entity.User;
import ua.nure.balagura.SummaryTask4.exception.AppException;
import ua.nure.balagura.SummaryTask4.web.command.Command;

public class RequestRegistrationCommand extends Command {

	private static final long serialVersionUID = 1409505939118770473L;
	private static final Logger LOG = Logger.getLogger(RequestRegistrationCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Commands starts");

		RequestDAO requestDao = new RequestDaoImpl();
		Request flightRequest = new Request();
		User self = (User) request.getSession().getAttribute("user");

		LOG.trace("Receive from JSP: name:seats:condetion--> " + request.getParameter("name") + ":"
				+ request.getParameter("seats") + ":" + request.getParameter("condition"));
		flightRequest.setDriverId(self.getId());
		flightRequest.setFlightId(Integer.parseInt(request.getParameter("flightId")));
		flightRequest.setSeats(Integer.parseInt(request.getParameter("seats")));
		flightRequest.setStatusId(Status.OPEN.ordinal());
		flightRequest.setProcessedBy(self.getId());
		if (requestDao.create(flightRequest))
			LOG.trace("Insert to DB: flightRequest --> " + flightRequest);

		LOG.debug("Commands finished");

		return Paths.COMMAND_LIST_REQUESTS;
	}

}
