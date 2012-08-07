package groupId.calc;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.InputMismatchException;

import javax.servlet.jsp.JspWriter;

public class Calculator {
	
	public String print(String fl, String expression, JspWriter out) throws IOException	{
    	BigDecimal result = new BigDecimal(0);
    	boolean flag = true;
   
        
       
        
        try	{
        	
        	if(fl.equals("true"))
        		flag = true;
        	else if(fl.equals("false"))
        		flag = false;
        	else
        		throw new InputMismatchException("Incorrect input. Enter true or false.<br>");
        	
        }	catch(InputMismatchException exp)	{
        	out.println(exp.getMessage() + "<br>");
        	return "";
        }
        
        App myApp = new App(flag);
          
        try	{
        	
        result = myApp.function(expression, out);
        return "<br>The result is " + result + "<br>";
  
        
        }	catch	(IllegalArgumentException exc)	{
        		out.println(exc.getMessage() + "<br>");
        		return "";
        		
        }	catch(ArithmeticException exc)	{
        		out.println(exc.getMessage() + "<br>");
        		return "";
        }
	}
	
	public static void main(String[] args) {
		
	}
}
