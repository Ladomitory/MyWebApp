<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyWebApp: User List</title>
</head>
<body>
    <header>
        <button onclick="location.href='/MyWebApp/changing-password'">Change password</button>
        <button onclick="location.href='/MyWebApp/logout'">Logout</button>
    </header>
    <%
        if (request.getSession().getAttribute("loginData") != null) {
            String loginData = (String) request.getSession().getAttribute("loginData");
            out.println("<div>" + loginData + "</div>");

            List<String> userList = (List<String>) request.getAttribute("userList");
            out.println("<div><ol>");
            for (String name : userList) {
                out.println("<li>" + name + "</li>");
            }
            out.println("</ol></div>");
        } else {
            out.println("<h1>403</h1>")
        }

    %>
</body>
</html>
