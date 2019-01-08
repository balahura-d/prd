package ua.nure.balagura.practice10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Part3")
public class Part3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String remove = request.getParameter("remove");
		if ("true".equals(remove)) 
			request.getSession().setAttribute("names", new ArrayList());
		response.sendRedirect("part3.jsp");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List names;
		HttpSession hs = request.getSession();
		if ((names = (List) hs.getAttribute("names")) == null)
			names = new ArrayList();
		String name = request.getParameter("inputName");
		names.add(name);
		hs.setAttribute("names", names);
		response.sendRedirect("part3.jsp");
	}

}
