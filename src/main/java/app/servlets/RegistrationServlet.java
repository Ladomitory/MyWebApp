package app.servlets;

import app.model.ModelController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class RegistrationServlet extends HttpServlet {
    private final String pageAddress = "pages/Registration.jsp";

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

        String name = req.getParameter("name");

        String _birthDate = req.getParameter("birthdate");
        LocalDate birthDate = LocalDate.parse(_birthDate);

        int errorCode = ModelController.getInstance().add(username, password, name, birthDate);
        req.setAttribute("regError", String.valueOf(errorCode));
        if (errorCode == 0) {
            req.setAttribute("regData", "0");
        }
        doGet(req, resp);
    }
}
