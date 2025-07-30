package servlet.auth;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AuthenticationService;

import java.io.IOException;

public abstract class BaseAuthenticationServlet extends HttpServlet {

    protected AuthenticationService authenticationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        authenticationService = (AuthenticationService) config.getServletContext().getAttribute("authenticationService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(getInitParameter("resourceName")).forward(req, resp);
    }
}
