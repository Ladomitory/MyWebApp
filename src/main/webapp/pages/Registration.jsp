<%--
  Created by IntelliJ IDEA.
  User: holmogorov.r
  Date: 29.11.2024
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyWebApp: Registration</title>
</head>
<body>
    <header>
        <button onclick="location.href='/MyWebApp'">Start page</button>
        <button onclick="location.href='/MyWebApp/login'">Login</button>
    </header>

    <main>
        <div>
            <h2>Registration page</h2>
        </div>
        <div>
            <%
                if (request.getAttribute("regData") == null) {
                    out.println("<form method=\"post\">\n" +
                            " <h3>Registration form</h3>\n" +
                            " <label>\n" +
                            "  Username: <input type=\"text\" name=\"username\" required/>\n" +
                            " </label>\n" +
                            " <br/>\n" +
                            " <label>\n" +
                            "  Name: <input type=\"text\" name=\"name\" required/>\n" +
                            " </label>\n" +
                            " <br/>\n" +
                            " <label>\n" +
                            "  BirthDate: <input type=\"date\" name=\"birthdate\" required/>\n" +
                            " </label>\n" +
                            " <br/>\n" +
                            " <label>\n" +
                            "  Password: <input type=\"password\" name=\"password\" required/>\n" +
                            " </label>\n" +
                            " <br/>\n" +
                            " <button type=\"submit\">Submit</button>\n" +
                            "</form>");
                }
            %>
            <%
                if (request.getAttribute("regError") != null) {
                    String regError = (String) request.getAttribute("regError");
                    if (regError.equals("0")) {
                        out.println("<h4>You have successfully registered</h4>");
                    } else if (regError.equals("101")) {
                        out.println("<h4>Username have no more 30 characters</h4>");
                    } else if (regError.equals("102")) {
                        out.println("<h4>Password have no more 30 characters</h4>");
                    } else if (regError.equals("103")) {
                        out.println("<h4>Name have no more 30 characters</h4>");
                    } else if (regError.equals("201")) {
                        out.println("<h4>Username is used</h4>");
                    } else if (regError.equals("104")) {
                        out.println("<h4>Date is later today</h4>");
                    }
                }
            %>
        </div>
    </main>
</body>
</html>
