<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyWebApp: Registration</title>
</head>
<body>
    <div>
      <h1>Registration Page</h1>
    </div>
    <div>
    <div>
    <h2>Registration Form</h2>
    </div>
        <form method="post">
            <label>
                Name: <input type="text" name="username">
            </label>
            <br/>
            <label>
                Password: <input type="password" name="password">
            </label>
            <br/>
            <button type="submit">Submit</button>
        </form>
        <%
            if (request.getAttribute("regError") != null) {
                String regError = (String) request.getAttribute("regError");
                if (regError.equals("0")) {
                    out.println("<div>Success</div>");
                } else if (regError.equals("1")) {
                    out.println("<div>Username is used</div>");
                } else {
                    out.println("<div>Error: Undefined error</div>");
                }
            }
        %>
    </div>
    <div>
        <button onclick="location.href='/MyWebApp/login'">Login</button>
    </div>
</body>
</html>
