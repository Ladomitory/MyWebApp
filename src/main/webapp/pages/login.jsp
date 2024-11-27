<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyWebApp: Login</title>
</head>
<body>
    <div>
        <h1>Login Page</h1>
    </div>
    <div>
        <div>
            <h2>Login Form</h2>
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
            if (request.getAttribute("loginError") != null) {
                String loginError = (String) request.getAttribute("loginError");
                if (loginError.equals("0")) {
                    out.println("<div>Success<br/><button onclick=\"location.href='/MyWebApp/user-list'\">User list</button></div>");

                } else if (loginError.equals("404")) {
                    out.println("<div>Invalid username or password</div>");
                } else {
                    out.println("<div>Error: Undefined error</div>");
                }
            }
        %>
    </div>
    <div>
        <button onclick="location.href='/MyWebApp/registration'">Registration</button>
    </div>
</body>
</html>
