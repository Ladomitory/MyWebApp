<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyWebApp: User List</title>
</head>
<body>
    <header>
        <%
            if (request.getSession().getAttribute("loginData") != null) {
                String loginData = (String) request.getSession().getAttribute("loginData");
                out.println(loginData + "  ");
                out.println("<button onclick=\"location.href='/MyWebApp/logout'\">Logout</button>");
            } else {
                out.println("<button onclick=\"location.href='/MyWebApp/login'\">Login</button>");
                out.println("<button onclick=\"location.href='/MyWebApp/reg'\">Registration</button>");
            }
        %>
    </header>
    <main>
        <div>
            <h2>User List</h2>
        </div>
        <%
            if (request.getSession().getAttribute("loginData") != null) {
                List<String> users = (List<String>) request.getAttribute("userList");
                out.println("<ol>");
                for (String user : users) {
                    out.println("<li>" + user + "</li>");
                }
                out.println("</ol>");
            }
        %>
    </main>
</body>
</html>