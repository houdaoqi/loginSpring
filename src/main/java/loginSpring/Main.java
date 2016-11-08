package loginSpring;

import loginSpring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import loginSpring.po.User;

import java.util.*;

/**
 * Created by lenovo on 10/27/2016.
 */

@Configuration
@ComponentScan
@Component
public class Main {

    @Autowired
    private IUserService userService;

    public void service(String name, String password){
        userService.saveUser(new User("hou", "123"));
        userService.verifyUser(name, password);
    }

    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Main main = context.getBean(Main.class);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = scanner.next();
        System.out.println("Enter your password:");
        String password = scanner.next();
        main.service(name, password);
    }
}

