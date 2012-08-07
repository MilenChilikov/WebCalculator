package groupId.calc;

import java.io.IOException;
import java.io.StreamCorruptedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "CalcServlet", urlPatterns = { "/CalcServlet" } )
public class CalcServlet  extends HttpServlet	{
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws IOException, ServletException	{
		String flag, expression;
		request.getParameter("flag");
		
		flag = request.getParameter("flag");
		expression = request.getParameter("expression");
	}
}
