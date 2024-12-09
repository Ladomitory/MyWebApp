<html>
<head>
    <title>MyWebApp: Start Page</title>
</head>
<body>
    <header>
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
    <div>
        <h2>Start page</h2>
    </div>
</body>
</html>
