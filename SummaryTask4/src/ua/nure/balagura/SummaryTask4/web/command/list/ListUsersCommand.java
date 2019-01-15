package ua.nure.balagura.SummaryTask4.web.command.list;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.balagura.SummaryTask4.consts.Paths;
import ua.nure.balagura.SummaryTask4.db.dao.UserDAO;
import ua.nure.balagura.SummaryTask4.db.dao.impl.UserDaoImpl;
import ua.nure.balagura.SummaryTask4.db.entity.Role;
import ua.nure.balagura.SummaryTask4.db.entity.User;
import ua.nure.balagura.SummaryTask4.exception.AppException;
import ua.nure.balagura.SummaryTask4.web.command.Command;

public class ListUsersCommand extends Command {

	private static final long serialVersionUID = -1226448381793052922L;
	private static final Logger LOG = Logger.getLogger(ListUsersCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		// TODO Auto-generated method stub
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		
		UserDAO userDao = new UserDaoImpl();
		String roleParam = request.getParameter("role");
		Role userToShowRole = Role.valueOf(roleParam.toUpperCase());
		List<User> usersList = userDao.selectUsersByRole(userToShowRole);
		LOG.trace("Found in DB: usersList --> " + usersList);

		session.setAttribute("userList", usersList);
		LOG.trace("Set the session attribute: usersList --> " + usersList);
		session.setAttribute("userToShowRole", userToShowRole);
		LOG.trace("Set the session attribute: userToShowRole --> " + userToShowRole);

		LOG.debug("Commands finished");
		return Paths.PAGE_USERS;
	}

}
