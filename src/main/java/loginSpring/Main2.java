package loginSpring;

import loginSpring.po.User;
import loginSpring.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * Created by lenovo on 10/27/2016.
 */

public class Main2 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        IUserService userService = (IUserService) context.getBean("userService");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = scanner.next();
        System.out.println("Enter your password:");
        String password = scanner.next();
        userService.saveUser(new User("hou", "123"));
        userService.verifyUser(name, password);
    }
}
