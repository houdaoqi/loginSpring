package loginSpring.dao.impl;

import loginSpring.common.LoginException;
import loginSpring.dao.IUserDao;
import loginSpring.common.ResultCode;
import loginSpring.po.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by lenovo on 10/28/2016.
 */
@Repository
public class UserDaoSqliteImpl implements IUserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public final void setJdbcTemplate(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public void save(User user) {

    }

    @Override
    public int update(User user) {
        return 0;
    }

    /**
     * insert the user into database
     * @param user the user to be inserted
     */
    @Override
    public void insert(User user) {
        String sql = "INSERT INTO user " +
                "(id, name, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, new Object[] { user.getId(),
                user.getUserName(),user.getUserPassword()
        });
    }

    /**
     * retrieve the user based on user name
     * @param toFindUser the user to be searched
     * @return the retrieved user
     */
    @Override
    public User select(User toFindUser){
        System.out.println("The select function in UserDaoSqliteImpl class is called");
        System.out.println("The toFindUser is:" + toFindUser.getUserName() +" "+ toFindUser.getUserPassword());
        String sql = "select id, name, password from user where name = ?";
        Object[] param = new Object[]{toFindUser.getUserName()};
        int[] types = {Types.VARCHAR};
        User result = null;
        try {
            result = jdbcTemplate.queryForObject(sql, param, types, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet resultSet, int i) throws SQLException {
                    User u = new User();
                    u.setId(resultSet.getInt(1));
                    u.setUserName(resultSet.getString(2));
                    u.setUserPassword(resultSet.getString(3));
                    return u;
                }
            });
        }catch (EmptyResultDataAccessException e){
            //e.printStackTrace();
            System.out.println("The exception is: " + e.getMessage());
            //throw new LoginException("No user found", e, ResultCode.NO_USER);
            return null;
        }
//        if(!result.getUserPassword().equals(toFindUser.getUserPassword())){
//            throw new LoginException("User found, but password invalid", new RuntimeException(), ResultCode.INVALID_PASS);
//        }
        //System.out.println(result.getName());
        //System.out.println(result.getPassword());
        return new User(result.getId(), result.getUserName(), result.getUserPassword());
    }
}
