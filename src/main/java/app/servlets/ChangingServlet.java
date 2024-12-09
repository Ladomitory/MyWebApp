package app.servlets;

import app.model.ModelController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ChangingServlet extends HttpServlet {
    private final String pageAddress = "pages/Changing.jsp";

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getSession().getAttribute("loginData") != null) {
            List<String> user = ModelController.getInstance().getUser((String) req.getSession().getAttribute("loginData"));
            req.setAttribute("username", user.get(0));
            req.setAttribute("o_name", user.get(1));
            req.setAttribute("o_birthdate", user.get(2));
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(pageAddress);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getSession().getAttribute("loginData") != null) {
            String username = (String) req.getSession().getAttribute("loginData");
            String oldPassword = req.getParameter("password");

            String newName = req.getParameter("c_name");
            int changingNameErrorCode = -1;
            if (!newName.isEmpty()) {
                changingNameErrorCode = ModelController.getInstance()
                        .changeName(username, oldPassword, newName);
                req.setAttribute("nameError", String.valueOf(changingNameErrorCode));
            }

            String newBirthDate = req.getParameter("c_birthdate");
            int changingBirthDateErrorCode = -1;
            if (!newBirthDate.isEmpty()) {
                changingBirthDateErrorCode = ModelController.getInstance()
                        .changeBirthDate(username, oldPassword, LocalDate.parse(newBirthDate));
                req.setAttribute("dateError", String.valueOf(changingBirthDateErrorCode));
            }

            String newPassword = req.getParameter("c_password");
            int changingPasswordErrorCode = -1;
            if (!newPassword.isEmpty()) {
                changingPasswordErrorCode = ModelController.getInstance()
                        .changePassword(username, oldPassword, newPassword);
                req.setAttribute("passError", String.valueOf(changingPasswordErrorCode));
            }

            if (changingNameErrorCode == 100 || changingBirthDateErrorCode == 100 || changingPasswordErrorCode == 100) {
                req.setAttribute("error", "invalid password");
            }
        }
        doGet(req, resp);
    }
}
