package groupId.calc;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;

import javax.servlet.jsp.JspWriter;

public class Calculator {
	
	public String print(String fl, String expression, Writer out) throws IOException	{
    	BigDecimal result = new BigDecimal(0);
    	boolean flag = true;
    
        flag = Boolean.parseBoolean(fl);
        	
        App myApp = new App(flag);
          
        try	{
        	
        result = myApp.function(expression, out);
        return "<br>The result is " + result + "<br>";
  
        
        }	catch	(IllegalArgumentException exc)	{
        	out.write(exc.getMessage() + "<br>");
        		return "";
        		
        }	catch(ArithmeticException exc)	{
        		out.write(exc.getMessage() + "<br>");
        		return "";
        }
	}
	
	public static void main(String[] args) {
		
	}
}
