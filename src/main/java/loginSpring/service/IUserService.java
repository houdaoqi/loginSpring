package loginSpring.service;

import loginSpring.common.LoginResponse;
import loginSpring.po.User;

/**
 * Created by lenovo on 10/27/2016.
 */
public interface IUserService {
    User findUser(User toFindUser);

    User findUserByName(String name);

    boolean isUserExist(String name);

    void insertUser(User user);

    void saveUser(User user);

    LoginResponse verifyUser(String name, String password);

}
