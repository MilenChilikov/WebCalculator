package groupId.calc;

import java.io.IOException;
import java.io.StreamCorruptedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CalcServlet  extends HttpServlet	{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws IOException, ServletException	{
		String flag, expression;
		request.getParameter("flag").toString();
		
//		flag = request.getParameter("flag");
//		expression = request.getParameter("expression");
	}
	public static void main(String[] args) {
		String str = " And \n now .. .	sad\tadsxczz xc\b";
		String temp = "";
		
		for(int i = 0; i < str.length(); ++i)	{
        	if(Character.isWhitespace(str.charAt(i))) 
        		System.out.println("Nice!  " + i);
		}
		System.out.println(str);
		 
		System.out.println(str.indexOf(" "));
		
		str = str.replaceAll("\\s", "");
		System.out.println(str + "!");
		
	}
}
