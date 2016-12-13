package loginSpring.dao;

import loginSpring.common.LoginResponse;
import loginSpring.po.User;

/**
 * Created by lenovo on 10/27/2016.
 */
public interface IUserDao {

    void save(User user);

    int update(User user);

    void insert(User user);

    User select(User user);
}
