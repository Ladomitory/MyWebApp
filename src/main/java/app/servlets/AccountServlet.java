package app.servlets;

import app.model.ModelController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AccountServlet extends HttpServlet {
    private final String pageAddress = "pages/Account.jsp";

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getSession().getAttribute("loginData") != null) {
            List<String> user = ModelController.getInstance().getUser((String) req.getSession().getAttribute("loginData"));
            req.setAttribute("i_username", user.get(0));
            req.setAttribute("i_name", user.get(1));
            req.setAttribute("i_birthdate", user.get(2));
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(pageAddress);
        dispatcher.forward(req, resp);
    }
}
