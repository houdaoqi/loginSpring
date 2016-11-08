package loginSpring.service.impl;

import loginSpring.common.LoginException;
import loginSpring.common.LoginResponse;
import loginSpring.common.ResultCode;
import loginSpring.dao.IUserDao;
import org.springframework.stereotype.Service;
import loginSpring.po.User;
import loginSpring.service.IUserService;

import javax.annotation.Resource;

/**
 * Created by lenovo on 10/27/2016.
 */
@Service
public class UserServiceImpl implements IUserService{

    //@Autowired
    @Resource(name="userDaoSqliteImpl")
    private IUserDao userDao;
/*
    public final IUserDao getUserDao() {
        return userDao;
    }
*/
    public final void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public User findUser(User toFindUser) throws LoginException{
//        try{
            User user=userDao.select(toFindUser);
//        }catch(LoginException e){
//            System.out.println("The Login Exception message has been handled.");
//        }
        return user;
    }

    @Override
    public User findUserByName(String name) {
        User user = new User();
        user.setUserName(name);
        return findUser(user);
    }

    @Override
    public boolean isUserExist(String name) {
        User user = findUserByName(name);
        return user != null;
    }
    public void saveUser(User user){
        userDao.save(user);
    }

    public void insertUser(User user){
        userDao.insert(user);
    }

    public LoginResponse verifyUser(String name, String password){
        User toFindUser = new User(name, password);
        //User user = null;
        User user = findUser(toFindUser);
        LoginResponse loginRes;
//        try {
//            user = findUser(toFindUser);
//        }catch (LoginException e){
//            loginRes = new LoginResponse(e.getErrorCode(), name);
//            return loginRes;
//        }
        if(user == null){
            loginRes = new LoginResponse(ResultCode.NO_USER);
        }else if(user.getUserPassword()==null || !user.getUserPassword().equals(password)){
            loginRes = new LoginResponse(ResultCode.INVALID_PASS, user.getId(), user.getUserName());
        }else {
            loginRes = new LoginResponse(ResultCode.PASS, user.getId(), user.getUserName());
        }
        return loginRes;
    }
}
