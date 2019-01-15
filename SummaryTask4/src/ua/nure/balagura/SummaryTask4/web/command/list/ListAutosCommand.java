package ua.nure.balagura.SummaryTask4.web.command.list;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.balagura.SummaryTask4.consts.Paths;
import ua.nure.balagura.SummaryTask4.db.dao.AutoFacadeDAO;
import ua.nure.balagura.SummaryTask4.db.dao.impl.AutoFacadeDaoImpl;
import ua.nure.balagura.SummaryTask4.db.facade.AutoFacade;
import ua.nure.balagura.SummaryTask4.exception.AppException;
import ua.nure.balagura.SummaryTask4.web.command.Command;

public class ListAutosCommand extends Command {

	private static final long serialVersionUID = 6799558168655371647L;

	private static final Logger LOG = Logger.getLogger(ListAutosCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Commands starts");

		HttpSession session = request.getSession();
		
		AutoFacadeDAO autoFacadeDao = new AutoFacadeDaoImpl();
		List<AutoFacade> autoList = autoFacadeDao.gettAll();
		LOG.trace("Found in DB: autoList --> " + autoList);

		session.setAttribute("autoList", autoList);
		LOG.trace("Set the session attribute: autoList --> " + autoList);

		LOG.debug("Commands finished");
		return Paths.PAGE_AUTOS;
	}

}
