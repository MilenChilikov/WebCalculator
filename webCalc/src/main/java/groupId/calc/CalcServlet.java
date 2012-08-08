package groupId.calc;

import java.io.IOException;
import java.io.PrintWriter;

//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(description = "CalcServlet", urlPatterns = { "/CalcServlet" } )
public class CalcServlet  extends HttpServlet	{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws IOException		{
		
		PrintWriter writer = response.getWriter();
		writer.println("<h3>CalcServlet<h3>");
		
		
		writer.println("<table>");
		writer.println("<form >");
	    writer.println("<br>");
	    writer.println("<tr>");
	    writer.println("<td>ShowSteps true/false: </td>");
	    writer.println("<td><input type=\"text\" name=\"flag\" /><td>");
	    writer.println("</tr>");
	    writer.println("<tr>");
	    writer.println("<td>Enter expression: </td>");
	    writer.println("<td><input type=\"text\" name=\"expression\" /></td>");
	    writer.println("</tr>");
	    writer.println("</table>");
	    writer.println("<br><input type=\"submit\" /> <br>");
	    writer.println("</form>");
	      
	    Calculator calc = new Calculator();
	    
	    if((request.getParameter("expression") != null && request.getParameter("flag") != null) ) {     
	            
	    	writer.println("<br>"); 
	    	if(request.getParameter("expression") != "")    {        
	    		writer.println("ShowSteps: " + request.getParameter("flag") + "<br>");
	    		writer.println("Expression: " + request.getParameter("expression") + "<br>");
	    		writer.println("<br>");          
	    		writer.println(calc.print(request.getParameter("flag"), request.getParameter("expression"), writer));
	        }
	    }
	}
}
