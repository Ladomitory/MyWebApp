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

public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        String loginData = (String) req.getSession().getAttribute("loginData");
        if (Model.getInstance().checkUsername(loginData)) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/changePassword.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            req.getSession().invalidate();
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/changePassword.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("loginData");
        String password = req.getParameter("password");
        String newPassword = req.getParameter("newPassword");
        User user = new User(username, password);
        if (Model.getInstance().checkUser(user)) {
            req.setAttribute("loginError", "0");
            Model.getInstance().changePassword(user, newPassword);
            doGet(req, resp);
        } else {
            req.setAttribute("loginError", "404");
            req.removeAttribute("password");
            doGet(req, resp);
        }
    }
}
