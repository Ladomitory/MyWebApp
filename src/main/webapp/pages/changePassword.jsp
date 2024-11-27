<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyWebApp: Change Password</title>
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
            Name: <input type="text" name="username"/>
        </label>
        <br/>
        <label>
            Old Password: <input type="password" name="password"/>
        </label>
        <br/>
        <label>
            New Password: <input type="password" name="newPassword"/>
        </label>
        <br/>
        <button type="submit">Submit</button>
    </form>
    <%
        if (request.getAttribute("loginError") != null) {
            String loginError = (String) request.getAttribute("loginError");
            if (loginError.equals("0")) {
                out.println("Success");
            } else if (loginError.equals("404")) {
                out.println("<div>Invalid username or password</div>");
            } else {
                out.println("<div>Error: Undefined error</div>");
            }
        }
    %>
    <div><button onclick="location.href='/MyWebApp/user-list'">User list</button></div>
</div>
<div>
    <button onclick="location.href='/MyWebApp/registration'">Registration</button>
</div>
</body>
</html>
