package loginSpring;

import loginSpring.common.LoginResponse;
import loginSpring.common.ResultCode;
import loginSpring.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.Scanner;

/**
 * Created by lenovo on 10/30/2016.
 */
public class Main4 {
    public static void loginResHandler(LoginResponse loginResponse){
        if(loginResponse.getCode().equals(ResultCode.PASS)){
            System.out.println("Login Succeed!");
        }else{
            System.out.println("Login failed!");
        }
    }
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext2.xml"});
        IUserService userService = (IUserService) context.getBean("userService");
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your name:");
            String name = scanner.next();
            System.out.println("Enter your password:");
            String password = scanner.next();
            LoginResponse loginResponse = userService.verifyUser(name, password);
            loginResHandler(loginResponse);
        }
    }
}
