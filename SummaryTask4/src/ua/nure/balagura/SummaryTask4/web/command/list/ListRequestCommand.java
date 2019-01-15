package ua.nure.balagura.SummaryTask4.web.command.list;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.balagura.SummaryTask4.consts.Paths;
import ua.nure.balagura.SummaryTask4.db.dao.RequestFacadeDAO;
import ua.nure.balagura.SummaryTask4.db.dao.impl.RequestFacadeDaoImpl;
import ua.nure.balagura.SummaryTask4.db.facade.RequestFacade;
import ua.nure.balagura.SummaryTask4.exception.AppException;
import ua.nure.balagura.SummaryTask4.web.command.Command;

public class ListRequestCommand extends Command {

	private static final long serialVersionUID = -9209538554527386391L;
	private static final Logger LOG = Logger.getLogger(ListRequestCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Commands starts");

		HttpSession session = request.getSession();
		
		RequestFacadeDAO requestFacadeDao = new RequestFacadeDaoImpl();
		List<RequestFacade> requestList = requestFacadeDao.getAll();
		LOG.trace("Found in DB: requestList --> " + requestList);
		
		for (RequestFacade requestFacade : requestList) {
			requestFacadeDao.addAvailableAuto(requestFacade);
		}
		
		session.setAttribute("requestList", requestList);
		LOG.trace("Set the session attribute: requestList --> " + requestList);

		LOG.debug("Commands finished");
		return Paths.PAGE_REQUESTS;
	}

}
