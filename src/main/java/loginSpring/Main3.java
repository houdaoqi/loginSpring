package loginSpring;

import loginSpring.po.User;
import loginSpring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Created by lenovo on 10/28/2016.
 */
@Configuration
@ComponentScan
@Component
public class Main3 {

    @Autowired
    private IUserService userService;

    @Bean(name = "dataSource")
    public DriverManagerDataSource getDatasource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();

        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:loginSpring.db");
        return dataSource;
    }

    @Bean(name="jdbcTemplate")
    public JdbcTemplate getJdbcTemplate(){
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(getDatasource());
        return jdbcTemplate;
    }

    public void service(String name, String password){
        userService.verifyUser(name, password);
    }

    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(Main3.class);
        Main3 main = context.getBean(Main3.class);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = scanner.next();
        System.out.println("Enter your password:");
        String password = scanner.next();
        main.service(name, password);
    }
}
