package app.servlets;

import app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        String loginData = (String) req.getSession().getAttribute("loginData");
        if (Model.getInstance().checkUsername(loginData)) {
            req.setAttribute("userList", Model.getInstance().getListUsernames());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/userList.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            req.getSession().invalidate();
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/userList.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

    }
}
