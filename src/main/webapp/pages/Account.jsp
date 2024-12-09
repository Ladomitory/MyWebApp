<%--
  Created by IntelliJ IDEA.
  User: holmogorov.r
  Date: 02.12.2024
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
      MyWebApp:
      <%
        if (request.getSession().getAttribute("loginData") != null) {
          out.println(" Account: " + (String) request.getSession().getAttribute("loginData"));
        } else {
          out.println("Not login");
        }
      %>
    </title>
</head>
<body>
<header>
  <button onclick="location.href='/MyWebApp'">Start page</button>
  <button onclick="location.href='/MyWebApp/users'">User list</button>
  <%
    if (request.getSession().getAttribute("loginData") != null) {
      String loginData = (String) request.getSession().getAttribute("loginData");
      out.println(loginData);
      out.println("<button onclick=\"location.href='/MyWebApp/logout'\">Logout</button>");
      out.println("<button onclick=\"location.href='/MyWebApp/changing'\">Changing account data</button>");
    } else {
      out.println("<button onclick=\"location.href='/MyWebApp/login'\">Login</button>");
      out.println("<button onclick=\"location.href='/MyWebApp/reg'\">Registration</button>");
    }
  %>
</header>
<main>
  <div>
    <div>
      <h2>Your Account</h2>
    </div>
    <%
      if (request.getSession().getAttribute("loginData") != null) {
        out.println("<div>");
        if (request.getAttribute("i_username") != null) {
          String username = (String) request.getAttribute("i_username");
          out.println("<h3>Username: " + username + "</h3>");
        }
        if (request.getAttribute("i_name") != null) {
          String name = (String) request.getAttribute("i_name");
          out.println("<h3>Name: " + name + "</h3>");
        }
        if (request.getAttribute("i_birthdate") != null) {
          String birthDate = (String) request.getAttribute("i_birthdate");
          out.println("<h3>BirthDate: " + birthDate + "</h3>");
        }
        out.println("</div>");
      } else {
        out.println("<div><h3>403</h3></div>");
      }
    %>
  </div>
</main>
</body>
</html>
