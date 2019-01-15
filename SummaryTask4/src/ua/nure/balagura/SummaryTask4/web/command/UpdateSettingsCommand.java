package ua.nure.balagura.SummaryTask4.web.command;

import java.io.IOException;

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

public class UpdateSettingsCommand extends Command {

	private static final long serialVersionUID = 7700966894974030207L;
	private static final Logger LOG = Logger.getLogger(UpdateSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		// obtain login and password from a request
		String login = request.getParameter("login");
		LOG.trace("Request parameter: login --> " + login);
		String password = request.getParameter("password");
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			throw new AppException("Login/password cannot be empty");
		}
		
		UserDAO dao = new UserDaoImpl();
		User user = dao.selectUserByLogin(login);
		LOG.trace("Found in DB: user --> " + user);

		if (user == null || !password.equals(user.getPassword())) {
			throw new AppException("Cannot find user with such login/password");
		}

		Role userRole = Role.getRole(user);
		LOG.trace("userRole --> " + userRole);

		session.setAttribute("user", user);
		LOG.trace("Set the session attribute: user --> " + user);
		session.setAttribute("userRole", userRole);
		LOG.trace("Set the session attribute: userRole --> " + userRole);
		
		String forward = Paths.PAGE_ERROR_PAGE;
		if (userRole != null) {
			forward = Paths.PAGE_MAIN;
		}
		
		LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());
		LOG.debug("Command finished");
		return forward;
	}

}