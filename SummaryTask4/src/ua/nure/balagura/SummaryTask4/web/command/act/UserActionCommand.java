package ua.nure.balagura.SummaryTask4.web.command.act;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.balagura.SummaryTask4.consts.Paths;
import ua.nure.balagura.SummaryTask4.db.dao.UserDAO;
import ua.nure.balagura.SummaryTask4.db.dao.impl.UserDaoImpl;
import ua.nure.balagura.SummaryTask4.db.entity.Role;
import ua.nure.balagura.SummaryTask4.db.entity.User;
import ua.nure.balagura.SummaryTask4.exception.DBException;
import ua.nure.balagura.SummaryTask4.web.command.Command;

public class UserActionCommand extends Command {

	private static final long serialVersionUID = -5841413768343992756L;
	private static final Logger LOG = Logger.getLogger(UserActionCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, DBException {
		LOG.debug("Commands starts");

		UserDAO userDao = new UserDaoImpl();
		User user = new User();
		String forward = Paths.PAGE_ERROR_PAGE;
		
		if (request.getParameter("regUser") != null) {
			LOG.debug("UserActionCommand#PARAMregUser");
			Role roleShownUsers = (Role) request.getSession().getAttribute("userToShowRole");
			LOG.trace("Receive from JSP: name:login:role--> " + request.getParameter("name") + ":"
					+ request.getParameter("login") + ":" + roleShownUsers.getName());
			user.setFullName(request.getParameter("name"));
			user.setLogin(request.getParameter("login"));
			user.setPassword(request.getParameter("login"));
			user.setRoleId(roleShownUsers.ordinal());
			if (userDao.create(user))
				LOG.trace("Insert to DB: user --> " + user);
			forward = Paths.COMMAND_LIST_USER + "&role=" + roleShownUsers.getName();
		} else if (request.getParameter("delUser") != null) {
			LOG.debug("UserActionCommand#PARAMdelUser");
			int userId = Integer.parseInt(request.getParameter("userId"));
			LOG.trace("Deleting user with id=" + userId);
			userDao.deleteById(userId);
			Role roleShownUsers = (Role) request.getSession().getAttribute("userToShowRole");
			forward = Paths.COMMAND_LIST_USER + "&role=" + roleShownUsers.getName();
		}

		LOG.debug("Commands finished");

		return forward;
	}

}
