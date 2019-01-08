package ua.nure.balagura.practice9;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Voting
 */
@WebServlet("/Voting")
public class Voting extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext servletContext = request.getServletContext();

		Map<String, Integer> voting = (Map<String, Integer>) servletContext.getAttribute("voting");

		String sport = request.getParameter("sport");

		Integer votes = voting.get(sport);

		voting.put(sport, votes + 1);

		servletContext.setAttribute("voting", voting);
		request.getRequestDispatcher("resultVoting.jsp").forward(request, response);

	}
}
