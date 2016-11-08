package loginSpring.sqlite;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 10/31/2016.
 */
//@Component
public class MyDriverManagerDataSource extends DriverManagerDataSource {

    DriverManagerDataSource dataSource;

    public MyDriverManagerDataSource() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:loginSpring.db");
    }
    public DriverManagerDataSource getMyDriverManagerDataSource(){
        return dataSource;
    }
    /*
    public void setDatasource(DriverManagerDataSource dataSource){
        this.dataSource = dataSource;
    }
    */
}
