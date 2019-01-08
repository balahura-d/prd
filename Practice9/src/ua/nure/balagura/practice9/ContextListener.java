package ua.nure.balagura.practice9;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext context = sce.getServletContext();
    	String str = context.getInitParameter("sports");
    	System.out.println(str);
    	
    	String[] ar = str.split(" ");
    	System.out.println(Arrays.toString(ar));
    	
    	List<String> sports = Arrays.asList(ar);
    	System.out.println(sports);
    	
    	Map<String, Integer> voting = new HashMap<String, Integer>();
    	
    	for (String sport : sports) {
			voting.put(sport, 0);
		}
    	
    	context.setAttribute("sports", sports);
    	context.setAttribute("voting", voting);
    	
    }

}