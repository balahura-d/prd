package ua.nure.balagura.SummaryTask4.web.command;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.balagura.SummaryTask4.consts.Paths;
import ua.nure.balagura.SummaryTask4.db.entity.User;
import ua.nure.balagura.SummaryTask4.exception.AppException;

public class ViewSettingsCommand extends Command {

	private static final long serialVersionUID = -1841329366327934663L;
	private static final Logger LOG = Logger.getLogger(ViewSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		String lang = request.getParameter("lang");
		LOG.trace("Choised language -->" + lang);

		HttpSession session = request.getSession();

		Locale locale = null;

		if (lang != null) {
			locale = new Locale(lang);
		} else {
			locale = request.getLocale();
		}
		
		//////////////////////////////////////////////////
		User user = (User)session.getAttribute("user");
		Cookie ck=new Cookie(user.getLogin(), lang);
		ck.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(ck);
		

		LOG.trace("Set session attrabute: locale --> " + locale.getDisplayName());
		session.setAttribute("locale", locale);
		
		
		

		LOG.debug("Command finished");
		return Paths.PAGE_MAIN;
	}

}
