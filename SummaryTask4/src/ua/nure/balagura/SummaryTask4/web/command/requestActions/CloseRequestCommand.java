package ua.nure.balagura.SummaryTask4.web.command.requestActions;

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

public class CloseRequestCommand extends Command {

	private static final long serialVersionUID = 1847417791290636L;
	private static final Logger LOG = Logger.getLogger(CloseRequestCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Commands starts");

		int requestId = Integer.parseInt(request.getParameter("requestId"));
		LOG.trace("Request parameter: requestId --> " + requestId);

		RequestDAO requestDao = new RequestDaoImpl();
		Request flightRequest = requestDao.selectById(requestId);
		LOG.trace("Receive from BD: request --> " + flightRequest);

		User processedBy = (User) request.getSession().getAttribute("user");
		LOG.trace("Receive from JSP: processedBy user --> " + processedBy);

		
		// НУЖНО ИЗМЕНЯТЬ ПОЛЬЗОВАТЕЛЯ "ОБРАБОТАНО..."
		flightRequest.setStatusId(Status.CLOSED.ordinal());
		flightRequest.setProcessedBy(processedBy.getId());
		requestDao.update(flightRequest);

		LOG.debug("Commands finished");
		return Paths.COMMAND_LIST_REQUESTS;
	}

	
}
