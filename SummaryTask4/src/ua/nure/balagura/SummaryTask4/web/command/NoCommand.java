package ua.nure.balagura.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.balagura.SummaryTask4.consts.Paths;

public class NoCommand extends Command {

	private static final long serialVersionUID = 327065135272249156L;
	private static final Logger LOG = Logger.getLogger(NoCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.debug("Command starts");

		String errorMessage = "No such command";
		request.getSession().setAttribute("errorMessage", errorMessage);
		LOG.error("Set the Session attribute: errorMessage --> " + errorMessage);

		LOG.debug("Command finished");
		return Paths.PAGE_ERROR_PAGE;
	}

}
