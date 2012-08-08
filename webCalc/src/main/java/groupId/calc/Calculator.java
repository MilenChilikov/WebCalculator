package groupId.calc;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;

public class Calculator {
	
	public String print(String fl, String expression, Writer out) throws IOException	{
    	BigDecimal result = new BigDecimal(0);
    	boolean flag = true;
    	
    	try	{
    	if(Boolean.parseBoolean(fl) || fl.isEmpty())
    		flag = true;
    	else if(fl.equals("false") || fl.equals("False"))	{
    		flag = false;
    	}
    	else
    		throw new IllegalArgumentException("Possible value of ShowSteps is true/false.");
    	}	catch(IllegalArgumentException exc)	{
    		out.write(exc.getMessage() + "<br>");
    		return "";
    	}
    	
    	App myApp = new App(flag);  
    	if(expression != "")	{
    	
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
    	else
			return "";
	}
}