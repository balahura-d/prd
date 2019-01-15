package ua.nure.balagura.SummaryTask4.web.command.act;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.balagura.SummaryTask4.consts.Paths;
import ua.nure.balagura.SummaryTask4.db.dao.AutoDAO;
import ua.nure.balagura.SummaryTask4.db.dao.impl.AutoDaoImpl;
import ua.nure.balagura.SummaryTask4.db.entity.Auto;
import ua.nure.balagura.SummaryTask4.exception.AppException;
import ua.nure.balagura.SummaryTask4.web.command.Command;

public class AutoActionsCommand extends Command {

	private static final long serialVersionUID = 1775095955901920483L;
	private static final Logger LOG = Logger.getLogger(AutoActionsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Commands starts");

		AutoDAO autoDao = new AutoDaoImpl();
		Auto auto = new Auto();

		if (request.getParameter("regAuto") != null) {
			LOG.debug("Register auto starts");
			LOG.trace("Receive from JSP: name:seats:condetion--> " + request.getParameter("name") + ":"
					+ request.getParameter("seats") + ":" + request.getParameter("condition"));
			auto.setName(request.getParameter("name"));
			auto.setSeats(Integer.parseInt(request.getParameter("seats")));
			auto.setConditionId(Integer.parseInt(request.getParameter("condition")));
			autoDao.create(auto);
		} else if (request.getParameter("sellAuto") != null) {
			LOG.debug("Delete auto starts");
			int id = Integer.parseInt(request.getParameter("autoId"));
			if (autoDao.deleteById(id))
				LOG.trace("Delete from DB: auto with id --> " + id);
		
		} else if (request.getParameter("repareAuto") != null) {
			int id = Integer.parseInt(request.getParameter("autoId"));
			auto = autoDao.selectById(id);
			auto.setConditionId(auto.getConditionId() - 1);
			autoDao.update(auto);
		} else if (request.getParameter("editAuto") != null) {
			int id = Integer.parseInt(request.getParameter("autoId"));
			auto = autoDao.selectById(id);
			auto.setName(request.getParameter("name"));
			auto.setSeats(Integer.parseInt(request.getParameter("seats")));
			autoDao.update(auto);
		}

		LOG.debug("Commands finished");

		return Paths.COMMAND_LIST_AUTOS;
	}

}
