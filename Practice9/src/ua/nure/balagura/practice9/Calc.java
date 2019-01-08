package ua.nure.balagura.practice9;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Calc
 */
@WebServlet("/Calc")
public class Calc extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int x = Integer.parseInt(request.getParameter("x"));
		int y = Integer.parseInt(request.getParameter("y"));
		String op = request.getParameter("op");
		int result = 0;
		switch (op) {
		case "minus":
			result = x - y;
			break;
		case "plus":
			result = x + y;
			break;
		}
		
		request.setAttribute("result", result);
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
		
//		response.setContentType("text/html");  
//		 PrintWriter out = response.getWriter();  
//		 out.println("<html>");
//		 out.println("<body>");
//		 out.println("<a href=\"calc.html\">to first page</a></br>");
//		 out.println(result);
//		 out.println("</body>");
//		 out.println("</html>");
	}
}
