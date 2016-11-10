package loginSpring.dao.impl;

import loginSpring.dao.IUserDao;
import org.springframework.stereotype.Repository;
import loginSpring.po.User;

import java.util.*;

/**
 * Created by lenovo on 10/27/2016.
 */
@Repository
public class UserDaoSimpleImpl implements IUserDao {

    private Map<String, String> map;

    public UserDaoSimpleImpl() {
        map = new HashMap<>();
    }

    public void save(User user) {
        if(update(user) == 0){
            insert(user);
        }
    }

    public int update(User user) {
        if(map.containsKey(user.getUserName())){
            map.put(user.getUserName(), user.getUserPassword());
            return 1;
        }else return 0;
    }

    public void insert(User user) {
        if(!map.containsKey(user.getUserName())) {
            map.put(user.getUserName(), user.getUserPassword());
            System.out.printf("user[%s] is inserted...\n", user);
        }
    }

    public User select(User user) {
        System.out.println("The select method in UserDaoSimpleImpl is called.");
        String password = map.get(user.getUserName());
        return new User(user.getUserName(), password);
    }
}
