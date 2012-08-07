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
    
    <table>
    <form >
        <br>
        <tr>
            <td>ShowSteps true/false: </td>
            <td><input type="text" name="flag" /><td>
        </tr>
        <tr>
            <td>Enter expression: </td>
            <td><input type="text" name="expression" /></td>
        </tr>
    </table>
        <br><input type="submit" /> <br>  
    </form>
       
        <% 
        Calculator calc = new Calculator();
        if((request.getParameter("expression") != null && request.getParameter("flag") != null) ) {
        %>
            <br>
            <%
            if(request.getParameter("expression") != "")    {
            %>
                ShowSteps: <%=request.getParameter("flag") %><br>
                Expression: <%=request.getParameter("expression") %><br>
            <br>
        <%
            }
            out.println(calc.print(request.getParameter("flag"), request.getParameter("expression"), out));
        }
        %> 
    </body>
</html>
