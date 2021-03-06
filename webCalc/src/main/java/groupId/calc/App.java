package groupId.calc;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.jsp.JspWriter;

public class App 
{
	public Map<Integer, BigDecimal> numbers = new HashMap<Integer, BigDecimal>();
	public Map<Integer, Integer> operations = new HashMap<Integer, Integer>();
	public Iterator<Integer> it;
	public char oper[];
	private boolean showSteps;
	
	App()	{
		showSteps = false;
	}
	
	App(boolean flag)	{
		showSteps = flag;
	}
	
	public boolean get()	{
		return showSteps;
	}
	
	public void set(boolean b)	{
		showSteps = b;
	}
	
	public boolean isShowSteps()	{
		return showSteps;
	}
	
	private static boolean  isOperation(char ch){
		boolean flag = false;
		switch(ch){
			case '+':
			case '-':
			case '*':
			case '/':
				flag = true;
				break;
		}
		
		return flag;
	}
	
	private static boolean isDot(char ch) {
		if(ch == '.')
			return true;
		else
			return false;
	}
	
	private static boolean  isParenthesis(char ch){
		boolean flag = false;
		switch(ch){
			case '(':
			case ')':
				flag = true;
				break;
		}
		
		return flag;
	}
	
	private static boolean isOpenParenthesis(char ch){
		if(ch == '(')
			return true;
		else 
			return false;
	}
	
	private static boolean isCloseParenthesis(char ch){
		if(ch == ')')
			return true;
		else 
			return false;
	}
		
	
	private static Integer getPriority(char ch){

		switch(ch)	{
			case '+':
			case '-':
				return 1;
			default:
				return 2;
		}
	}
	
	private static BigDecimal getResultOf(BigDecimal a, BigDecimal b, char c)	{
		
		switch(c)	{
			case '+':
				return a.add(b);
			case '-':
				return a.subtract(b);
			case '*':
				return a.multiply(b);
			default:
				return  a.divide(b, 20, RoundingMode.HALF_UP);
		}
	}
	
	private void input(String str)	{
		
		char ch;
    	int flag = 1, countP = 0;
    	boolean openP = false, signFlag = false,  dotFlag = false;
    	
    	Integer numKey = 1;
    	String temp = "";
    	
        for(int i = 0; i < str.length(); ++i)	{
        	if(Character.isWhitespace(str.charAt(i)))	{
        		if(i == 0 || i == str.length()-1)
        			continue;
        		else if((Character.isDigit(str.charAt(i-1)) || isDot(str.charAt(i-1))) && 
        				(Character.isDigit(str.charAt(i+1)) || isDot(str.charAt(i+1))))
        				throw new IllegalArgumentException("Position " + (i+1) + ": Whitespace between number. <br>" );
        	}
        	else
        		temp += str.charAt(i);
        }
    	
        str = temp;
        temp = "";
    	
    	oper = new char[str.length()/2 + 1];
   
        for(int i = 0; i < str.length(); ++i)	{
        	ch = str.charAt(i);
        	
        	if(Character.isDigit(ch) || isOperation(ch) || isParenthesis(ch) || isDot(ch))	{
        		if(isCloseParenthesis(ch))	{
        			if(flag == 1 && openP && signFlag)	{
           				numbers.put(numKey, new BigDecimal(temp).negate());
        				temp = "";
        				flag = 0;
        				dotFlag = openP = signFlag = false;
        			}
          			
        				--countP;
        			
        			if(countP < 0)	{
  
        				throw new IllegalArgumentException("Position " + (i+1) + ": Incorrect input of Close Parenthesis.<br>");
        			}
        			
        		}
        		else if(flag == 1 && openP && signFlag && (isDot(ch) || Character.isDigit(ch))){
        			if(isDot(ch))	{
        				if(dotFlag)	{
            				throw new IllegalArgumentException("Position " + (i+1) + ": Incorrect input of Dot.<br>");
            			}
            			dotFlag = true;
            			temp += ch;
        			}
        			else	{
        				
            			temp += ch;
        			}
        		}
        		else if(flag == 1 && openP && ch == '-')	{
        			if(signFlag)	{
        				throw new IllegalArgumentException("Position " + (i+1) + ": Incorrect input. Expect number.<br>");
        			}
        			signFlag = true;
        		}
        		else if(flag == 1 && Character.isDigit(ch))	{
        			if(i  != str.length()-1 && (Character.isDigit(str.charAt(i+1)) || isDot(str.charAt(i+1))))	{
        				temp += ch;
        				continue;
        			}
        			
        			temp += ch;
        			numbers.put(numKey, new BigDecimal(temp));
        			temp = "";
        			flag = 0;
        			dotFlag = openP = signFlag = false;
        		}
        		
        		else if(flag == 1 && isDot(ch))	{
        			if(dotFlag)	{
        				throw new IllegalArgumentException("Position " + (i+1) + ": Incorrect input of Dot.<br>");
        			}
        			dotFlag = true;
        			temp += ch;
        		}
        		
        		else if(flag == 1 && isOpenParenthesis(ch))	{
        			++countP;
        			openP = true;
        		}
        		
        		else if(flag == 0 && isOperation(ch))	{
        			
        			Integer prior = 0;
        			prior = getPriority(ch) + 3*countP;
        			operations.put(numKey, prior);
        			flag = 1;
        			oper[numKey] = ch;
        			dotFlag = openP = signFlag = false;
        			++numKey;
        		}
        		
        		else {
        			if(flag == 0)
        				throw new IllegalArgumentException("Position " + (i+1) + ": Expect operation.<br>");
        			else if (flag == 1)
        				throw new IllegalArgumentException("Position " + (i+1) + ": Expect number.<br>");
        			else
        				throw new IllegalArgumentException("Invalid input.<br>");
        		}
        	}
        	
        	else	{
        		
        		throw new IllegalArgumentException(" Input correct expression. For example  3*4-2.<br>");
        	}
        }
        
        if(countP != 0)	{
        	
        	throw new IllegalArgumentException("You haven't close all Parentheses.<br>");
        }
        
        if(flag == 1)	{
        	
        	throw new IllegalArgumentException("You haven't input number after last operation.<br>");
        }
        
		
		return ;
        
	}
	

	private BigDecimal parse(Writer out) throws IOException{
		
        it = operations.keySet().iterator();
        int privKey, currKey, nextKey, len = numbers.size();
        BigDecimal result = null;
        
        privKey = currKey = (Integer) it.next();
        
        if(it.hasNext())
        	nextKey = (Integer) it.next();
        else	{
        	
        	if(numbers.get(currKey+1).doubleValue() == 0 && oper[currKey] == '/')	{
        		out.write("Trying to compute " + roundBD(numbers.get(currKey)) + oper[currKey] + roundBD(numbers.get(currKey+1)) + "<br>");
        		throw new ArithmeticException("Can't divide by zero.<br>");
        	}
        	
        	if(isShowSteps())	{
        		
 
				out.write("Compute " + roundBD(numbers.get(currKey)) + " " + oper[currKey] + " " + roundBD(numbers.get(currKey+1)) + 
					" = " + roundBD(getResultOf(numbers.get(currKey), numbers.get(currKey+1), oper[currKey])) + "<br>");
			}
        	
        	result = getResultOf(numbers.get(currKey), numbers.get(currKey+1), oper[currKey]);
        	
        	return result;
        }
     
        while(!operations.isEmpty())	{
        
        	while(it.hasNext())	{
        		
        		if(operations.get(privKey) <= operations.get(currKey) && operations.get(currKey) >= operations.get(nextKey))	{
        			
        			if(numbers.get(nextKey).doubleValue() == 0 && oper[currKey] == '/')	{
        				out.write("Trying to compute " + roundBD(numbers.get(currKey)) + oper[currKey] + roundBD(numbers.get(nextKey)) + "<br>");
        				throw new ArithmeticException("Can't divide by zero.<br>");
                	}
        			
        			if(isShowSteps())	{
        				out.write("Compute " + roundBD(numbers.get(currKey)) + " " + oper[currKey] + " " + roundBD(numbers.get(nextKey)) + 
        					" = " + roundBD(getResultOf(numbers.get(currKey), numbers.get(nextKey), oper[currKey])) + "<br>");
        			}
        				
        			numbers.put(nextKey, getResultOf(numbers.get(currKey), numbers.get(nextKey), oper[currKey]));	
        			numbers.remove(currKey);
        			operations.remove(currKey);
        			
        			it = operations.keySet().iterator();
        			privKey = currKey = it.next();
                    nextKey = (Integer) it.next();
        		}
        		
        		else 	{
        			privKey = currKey = nextKey;
        			nextKey = (Integer) it.next();
        		}
        	}
        	
        	if(operations.size() == 1)	{
        		
        		it = operations.keySet().iterator();
    			currKey = it.next();
    			
    			if(numbers.get(len).doubleValue() == 0 && oper[currKey] == '/')	{
    				out.write("Trying to compute " + roundBD(numbers.get(currKey)) + oper[currKey] + roundBD(numbers.get(len)) + "<br>");
    				throw new ArithmeticException("Can't divide by zero.<br>");
            	}
    			
    			if(isShowSteps())	{
    				out.write("Compute " + roundBD(numbers.get(currKey)) + " " + oper[currKey] + " " + roundBD(numbers.get(len)) + 
    					" = " + roundBD(getResultOf(numbers.get(currKey), numbers.get(len), oper[currKey])) + "<br>");
    			}
    				
            	result = getResultOf(numbers.get(currKey), numbers.get(len), oper[currKey]);
            	
            	return result;
        	}
        	
        	else if(operations.get(currKey) >= operations.get(nextKey) && operations.get(currKey) >= operations.get(privKey))	{ 
        		
        		if(numbers.get(nextKey).doubleValue() == 0 && oper[currKey] == '/')	{
        			out.write("Trying to compute " + roundBD(numbers.get(currKey)) + oper[currKey] + roundBD(numbers.get(currKey)) + "<br>");
        			throw new ArithmeticException("Can't divide by zero.<br>");
            	}
        		
        		if(isShowSteps())	{
        			out.write("Compute " + roundBD(numbers.get(currKey)) + " " + oper[currKey] + " " + roundBD(numbers.get(nextKey)) + 
       					" = " + roundBD(getResultOf(numbers.get(currKey), numbers.get(nextKey), oper[currKey])) + "<br>");
        		}
        			
       			numbers.put(nextKey, getResultOf(numbers.get(currKey), numbers.get(nextKey), oper[currKey]));
       			numbers.remove(currKey);
       			operations.remove(currKey);
       			
       			it = operations.keySet().iterator();
       			privKey = currKey = it.next();
       			
       			if(operations.size() != 1)
       				nextKey = it.next();
       		}

        	else if(operations.get(nextKey) >= operations.get(currKey))	{
        		
        		if(numbers.get(len).doubleValue() == 0 && oper[nextKey] == '/')	{
        			out.write("Trying to compute " + roundBD(numbers.get(nextKey)) + oper[nextKey] + roundBD(numbers.get(len)) + "<br>");
            		throw new ArithmeticException("Can't divide by zero.<br>");
            		
            	}
        		
        		if(isShowSteps())	{
        			out.write("Compute " + roundBD(numbers.get(nextKey)) + " " + oper[nextKey] + " " + roundBD(numbers.get(len)) + 
    					" = " + roundBD(getResultOf(numbers.get(nextKey), numbers.get(len), oper[nextKey])) + "<br>");
        		}
        			
        		numbers.put(len, getResultOf(numbers.get(nextKey), numbers.get(len), oper[nextKey]));
        		numbers.remove(nextKey);
        		operations.remove(nextKey);
        		
        		it = operations.keySet().iterator();
        		privKey = currKey = it.next();
        		
        		if(operations.size() != 1)
       				nextKey = it.next();
        	}
        }
        
        return result;	
	}
	
	private BigDecimal roundBD(BigDecimal b)	{
		
		String str = b.toString();
		
		if(str.indexOf('.') == -1)
			return b;
		if(b.subtract(b.setScale(0, BigDecimal.ROUND_FLOOR)) == new BigDecimal(0))
				return b.setScale(0, BigDecimal.ROUND_FLOOR);
	
		int len = str.length()-1 - str.indexOf('.');

		for(int i = str.length()-1; i >= 0; --i)	{
			if(str.charAt(i) == '0')
				--len;
			else 
				break;
		}
		
		b = b.setScale(len, BigDecimal.ROUND_HALF_UP);
		
		return b;
	}
	
	public BigDecimal function(String str, Writer out) throws IOException	{
		input(str);
		return roundBD(parse(out));
	}
	
	private void show()	{
		it = numbers.keySet().iterator();
		System.out.println("Numbers: ");
		
		while(it.hasNext()){
			Integer i = it.next();
			System.out.println("Key " + i + ", Value " + numbers.get(i));
		}
		System.out.println("Operators ");
		it = operations.keySet().iterator();
		while(it.hasNext()){
			Integer i = it.next();
			System.out.println("Key " + i + ", operation " + oper[i] + ", Priority " + numbers.get(i));
		}
	}
}