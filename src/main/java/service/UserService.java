package service;

import dao.UserDao;
import entity.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    //UserDao userDao = new UserDao();

    public void save(User user) {
        userDao.save(user);
    }
}
