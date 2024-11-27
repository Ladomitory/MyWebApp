package app.servlets;

import app.entities.User;
import app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (Model.getInstance().checkUser(new User(username, password))) {
            req.setAttribute("loginError", "0");
            HttpSession session = req.getSession();
            session.setAttribute("loginData", username);
            session.setMaxInactiveInterval(-1);
            doGet(req, resp);
        } else {
            req.setAttribute("loginError", "404");
            req.removeAttribute("password");
            doGet(req, resp);
        }
    }
}
