package util;

import entity.Credentials;
import jakarta.servlet.http.HttpServletRequest;

public class CredentialsExtractor {
    public Credentials extract(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        return Credentials.builder().login(login).password(password).build();
    }
}
