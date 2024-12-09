<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>MyWebApp: Login</title>
  </head>
  <body>
    <header>
        <button onclick="location.href='/MyWebApp'">Start page</button>
        <%
        if (request.getSession().getAttribute("loginData") != null) {
          String loginData = (String) request.getSession().getAttribute("loginData");
          out.println("<button onclick=\"location.href='/MyWebApp/users'\">User List</button>");
          out.println("<a href=\"/MyWebApp/account\">" + loginData + "</a>");
          out.println("<button onclick=\"location.href='/MyWebApp/logout'\">Logout</button>");
        } else {
          out.println("<button onclick=\"location.href='/MyWebApp/reg'\">Registration</button>");
        }
        %>
    </header>
    <main>
      <div>
        <h2>Login page</h2>
      </div>
      <div>
        <%
          if (request.getSession().getAttribute("loginData") == null) {
            out.println("<form method=\"post\">\n" +
                    " <h3>Login form</h3>\n" +
                    " <label>\n" +
                    "  Username: <input type=\"text\" name=\"username\"/>\n" +
                    " </label>\n" +
                    " <br/>\n" +
                    " <label>\n" +
                    "  Password: <input type=\"password\" name=\"password\"/>\n" +
                    " </label>\n" +
                    " <br/>\n" +
                    " <button type=\"submit\">Submit</button>\n" +
                    "</form>");
        }
        %>
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
<%--      <div>--%>
<%--        <%--%>
<%--          if (request.getSession().getAttribute("loginData") != null) {--%>
<%--            out.println("<button onclick=\"location.href='/MyWebApp/users'\">User List</button>");--%>
<%--          }--%>
<%--        %>--%>
<%--     </div>--%>
    </main>
  </body>
</html>
