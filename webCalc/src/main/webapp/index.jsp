<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="groupId.calc.Calculator"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    
    <form >
        <br>ShowSteps true/false: <input type="text" name="flag" />
        <br>Enter expression: <input type="text" name="expression" />
        <br><input type="submit" />
    
    </form>
         
        <% 
        try {
        Calculator calc = new Calculator();
        String s1 = request.getParameter("flag");
        String s2 = request.getParameter("expression");
        //PrintWriter output = response.getWriter();
        out.println(calc.print(s1, s2, out));
        }   catch(NullPointerException exp) {
        
            }
        %>
        <%=request.getParameter("expression") %>
        
        
        <br>Success!
    </body>
</html>
