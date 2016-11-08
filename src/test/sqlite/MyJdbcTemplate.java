package loginSpring.sqlite;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


/**
 * Created by lenovo on 10/31/2016.
 */
//@Component
public class MyJdbcTemplate extends JdbcTemplate{



    public MyJdbcTemplate(){
        super();

    }

    public MyJdbcTemplate(DriverManagerDataSource dataSource){
        super(dataSource);
    }
    public void setDataSource(DriverManagerDataSource dataSource){
        super.setDataSource(dataSource);
    }

    public JdbcTemplate getMyJdbcTemplate(){
        return this;
    }
}
