package app.servlets;

import app.model.ModelController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private final String pageAddress = "pages/Login.jsp";

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(pageAddress);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (ModelController.getInstance().checkUser(username, password)) {
            req.getSession().setAttribute("loginData", username);
            req.getSession().setMaxInactiveInterval(-1);
            req.removeAttribute("loginError");
            doGet(req, resp);
        } else {
            req.setAttribute("loginError", "404");
            doGet(req, resp);
        }
    }
}
