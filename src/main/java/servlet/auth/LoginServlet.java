package servlet.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        urlPatterns = "/login",
        initParams = {@WebInitParam(name = "resourceName", value = "/login.jsp")})

public class LoginServlet extends BaseAuthenticationServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        authenticationService.authenticate(req, resp);

        if (authenticationService.isAuthenticated(req)) {
            resp.sendRedirect("/secure/products");
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }
}
