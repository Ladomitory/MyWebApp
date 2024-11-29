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
            <form method="post">
                <h3>Registration form</h3>
                <label>
                    Username: <input type="text" name="username" required/>
                </label>
                <br/>
                <label>
                    Name: <input type="text" name="name" required/>
                </label>
                <br/>
                <label>
                    BirthDate: <input type="date" name="birthDate" required/>
                </label>
                <br/>
                <label>
                    Password: <input type="password" name="password" required/>
                </label>
                <br/>
                <button type="submit">Submit</button>
            </form>
            <%
                if (request.getAttribute("regError") != null) {
                    String regError = (String) request.getAttribute("regError");
                    if (regError.equals("0")) {
                        out.println("<h4>Success, go on</h4>");
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
