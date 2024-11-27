package app.servlets;

import app.model.ModelController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserListServlet extends HttpServlet {
    private final String pageAddress = "pages/UserList.jsp";

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("userList", ModelController.getInstance().getListUsers());
        RequestDispatcher dispatcher = req.getRequestDispatcher(pageAddress);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

    }
}
