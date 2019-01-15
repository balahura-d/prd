package ua.nure.balagura.SummaryTask4.web.command.requestActions;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.balagura.SummaryTask4.consts.Paths;
import ua.nure.balagura.SummaryTask4.db.dao.AutoDAO;
import ua.nure.balagura.SummaryTask4.db.dao.RequestDAO;
import ua.nure.balagura.SummaryTask4.db.dao.RequestFacadeDAO;
import ua.nure.balagura.SummaryTask4.db.dao.impl.AutoDaoImpl;
import ua.nure.balagura.SummaryTask4.db.dao.impl.RequestDaoImpl;
import ua.nure.balagura.SummaryTask4.db.dao.impl.RequestFacadeDaoImpl;
import ua.nure.balagura.SummaryTask4.db.entity.Auto;
import ua.nure.balagura.SummaryTask4.db.entity.Request;
import ua.nure.balagura.SummaryTask4.db.entity.User;
import ua.nure.balagura.SummaryTask4.exception.AppException;
import ua.nure.balagura.SummaryTask4.exception.DBException;
import ua.nure.balagura.SummaryTask4.web.command.Command;

public class ApplyRequestCommand extends Command {

	private static final long serialVersionUID = 1847417791290636L;
	private static final Logger LOG = Logger.getLogger(ApplyRequestCommand.class);

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

		
		User processed = (User) request.getSession().getAttribute("user");
		
		int autoId = Integer.parseInt(request.getParameter("autoId"));
		AutoDAO autoDao = new AutoDaoImpl();
		Auto auto = autoDao.selectById(autoId);
		
		RequestFacadeDAO rfDao = new RequestFacadeDaoImpl();
		try {
			rfDao.applyRequest(flightRequest, processed, auto);
		} catch (SQLException e) {
			throw new DBException("SQL exception (request or table failed): " + e, e);
		}

		LOG.debug("Commands finished");
		return Paths.COMMAND_LIST_REQUESTS;
	}


	}

