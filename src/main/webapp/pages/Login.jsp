<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>MyWebApp: Login</title>
  </head>
  <body>
    <header>
      <%
        if (request.getSession().getAttribute("loginData") != null) {
          String loginData = (String) request.getSession().getAttribute("loginData");
          out.println(loginData + "  ");
          out.println("<button onclick=\"location.href='/MyWebApp/logout'\">Logout</button>");
        } else {
          out.println("<button onclick=\"location.href='/MyWebApp/reg'\">Registration</button>");
        }
      %>
    </header>
    <div>
      <h2>Login page</h2>
    </div>
    <div>
      <form method="post">
        <h3>Login form</h3>
        <label>
          Username: <input type="text" name="username"/>
        </label>
        <br/>
        <label>
          Password: <input type="password" name="password"/>
        </label>
        <br/>
        <button type="submit">Submit</button>
      </form>
      <%
        if (request.getAttribute("loginError") != null) {
          String error = (String) request.getAttribute("loginError");
          if (error.equals("404")) {
            out.println("<h4>Invalid username or password</h4>");
          }
        } else if (request.getSession().getAttribute("loginData") != null) {
          out.println("<h4>Success</h4>");
        }
      %>
    </div>
    <div>
      <%
        if (request.getSession().getAttribute("loginData") != null) {
          out.println("<button onclick=\"location.href='/MyWebApp/users'\">User List</button>");
        }
      %>
    </div>
  </body>
</html>
