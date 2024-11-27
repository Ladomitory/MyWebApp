package app.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    private final String pageAddress = "pages/Logout.jsp";

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().removeAttribute("loginData");
        RequestDispatcher dispatcher = req.getRequestDispatcher(pageAddress);
        dispatcher.forward(req, resp);
    }
}
