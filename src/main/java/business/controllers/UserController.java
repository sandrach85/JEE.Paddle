package business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.wrapper.UserWrapper;
import data.daos.AuthorizationDao;
import data.daos.UserDao;
import data.entities.Authorization;
import data.entities.Role;
import data.entities.User;

@Controller
public class UserController {

    private UserDao userDao;

    private AuthorizationDao authorizationDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    public boolean registration(UserWrapper userWrapper) {
        User user = new User(userWrapper.getUsername(), userWrapper.getEmail(), userWrapper.getPassword(), userWrapper.getBirthDate());
        try {
            userDao.save(user);
            authorizationDao.save(new Authorization(user, Role.PLAYER));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
