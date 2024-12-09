<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyWebApp: Changing account data</title>
</head>
<body>
<header>
  <button onclick="location.href='/MyWebApp'">Start page</button>
  <button onclick="location.href='/MyWebApp/users'">User list</button>
  <%
    if (request.getSession().getAttribute("loginData") != null) {
      String loginData = (String) request.getSession().getAttribute("loginData");
      out.println("<a href=\"/MyWebApp/account\">" + loginData + "</a>");
      out.println("<button onclick=\"location.href='/MyWebApp/logout'\">Logout</button>");
    } else {
      out.println("<button onclick=\"location.href='/MyWebApp/login'\">Login</button>");
      out.println("<button onclick=\"location.href='/MyWebApp/reg'\">Registration</button>");
    }
  %>
</header>
<main>
  <div>
    <form method="post">
      <h3>Changing Form</h3>
        <%
          if (request.getSession().getAttribute("loginData") != null) {
            if (request.getAttribute("username") != null) {
              String username = (String) request.getAttribute("username");
              out.println("Username: " + username + "<br/>");
            }
          } else {
            out.println("403<br/>");
          }
        %>
      <label>
        <%
          if (request.getSession().getAttribute("loginData") != null) {
            if (request.getAttribute("o_name") != null) {
              String name = (String) request.getAttribute("o_name");
              out.println("Name: " + name + "<br/>");
            }
          } else {
            out.println("403<br/>");
          }
        %>
        New Name: <input type="text" name="c_name"/>
        <%
          if (request.getAttribute("nameError") != null) {
            String error = (String) request.getAttribute("nameError");
            if (error.equals("103")) {
              out.println("103");
            } else if (error.equals("0")) {
              out.println("Success");
            }
          }
        %>
      </label>
      <br/>
      <label>
        <%
          if (request.getSession().getAttribute("loginData") != null) {
            if (request.getAttribute("o_birthdate") != null) {
              String birthDate = (String) request.getAttribute("o_birthdate");
              out.println("BirthDate: " + birthDate + "<br/>");
            }
          } else {
            out.println("403<br/>");
          }
        %>
        New Birth Date: <input type="date" name="c_birthdate"/>
        <%
          if (request.getAttribute("dateError") != null) {
            String error = (String) request.getAttribute("dateError");
            if (error.equals("103")) {
              out.println("103");
            } else if (error.equals("0")) {
              out.println("Success");
            }
          }
        %>
      </label>
      <br/>
      <label>
        New Password: <input type="password" name="c_password"/>
        <%
          if (request.getAttribute("passError") != null) {
            String error = (String) request.getAttribute("passError");
            if (error.equals("103")) {
              out.println("103");
            } else if (error.equals("0")) {
              out.println("Success");
            }
          }
        %>
      </label>
      <br/>
      <label>
        Old Password: <input type="password" name="password"/>
        <%
          if (request.getAttribute("error") != null) {
            out.println((String) request.getAttribute("error"));
          }
        %>
      </label>
      <br/>
      <button type="submit">Update</button>
    </form>
  </div>
</main>
</body>
</html>
