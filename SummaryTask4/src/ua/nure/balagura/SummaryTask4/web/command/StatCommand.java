package ua.nure.balagura.SummaryTask4.web.command;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.balagura.SummaryTask4.consts.Paths;
import ua.nure.balagura.SummaryTask4.db.dao.FlightFacadeDAO;
import ua.nure.balagura.SummaryTask4.db.dao.impl.FlightFacadeDaoImpl;
import ua.nure.balagura.SummaryTask4.exception.AppException;

public class StatCommand extends Command {

	private static final long serialVersionUID = -4326409788351070394L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		String strFrom = request.getParameter("from");
		if (strFrom.length() == 0) {
			Object ff = null;
			request.getSession().setAttribute("ff", ff);
			return Paths.PAGE_MAIN;
		}
			
		String strTo = request.getParameter("to");
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern("yyyy-MM-dd");
		Date from = null;
		Date to = null;
		try {
			from = format.parse(strFrom);
			if (strTo.length() == 0)
				to = new Date();
			else
				to = format.parse(strTo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FlightFacadeDAO ffd = new FlightFacadeDaoImpl();

		int[] ff = ffd.getStat(from, to);

		request.getSession().setAttribute("from", from);
		request.getSession().setAttribute("to", to);
		request.getSession().setAttribute("ff", ff);
		// TODO Auto-generated method stub
		return Paths.PAGE_MAIN;
	}
}
