package service;

import entity.Credentials;
import entity.LoginAttempt;
import entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import util.CredentialsExtractor;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class AuthenticationService {

    private final Map<String, LoginAttempt> loginAttempts = new ConcurrentHashMap<>();

    private final UserService userService;

    private final CredentialsExtractor credentialsExtractor;

    public boolean register(HttpServletRequest req) {
        Credentials credentials = credentialsExtractor.extract(req);
        if (userService.isExist(credentials.getLogin())) {
            return false;
        } else {
            userService.save(credentials.getLogin(), credentials.getPassword());
            return true;
        }
    }

    public void authenticate(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Credentials credentials = credentialsExtractor.extract(req);

        String login = credentials.getLogin();
        String password = credentials.getPassword();

        loginAttempts.putIfAbsent(login, new LoginAttempt());
        LoginAttempt attempt = loginAttempts.get(login);

        if (attempt.isBlockedExpired()) {
            attempt.setCountOfAttempts(new AtomicInteger(0));
        }

        if (attempt.isBlocked()) {
            req.setAttribute("AuthMessageError", "Username or password is incorrect");
        }

        Optional<User> optionalUser = userService.findUserByCredentials(login, password);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            req.getSession().setAttribute("user", user);
//            onlineUsersSet.add(user.getLogin());
        } else {
            attempt.incrementCountOfAttempts();
        }
    }

    public boolean isAuthenticated(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        return user != null;
    }
}
