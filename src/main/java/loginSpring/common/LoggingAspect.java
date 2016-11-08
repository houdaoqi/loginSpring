package loginSpring.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * Created by lenovo on 10/31/2016.
 */
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    /**
     * This is the method which I would like to execute
     * before a selected method execution.
     */
    public void beforeAdvice(){
        logger.trace("Going to verify user information.");
        System.out.println("Going to verify user information.");
    }

    /**
     * This is the method which I would like to execute
     * after a selected method execution.
     */

    public void afterAdvice(){
        logger.trace("The verification function ended.");
        System.out.println("The verification function ended.");
    }


    /**
     * This is the method which I would like to execute
     * when method returns.
     */
    public void afterReturningAdvice(Object retVal){
        LoginResponse response = (LoginResponse)retVal;
        if(retVal != null) {
//            logger.trace("Returning: {}", retVal.toString());
//            System.out.println("Returning: " + retVal.toString());
            logger.trace("Returning: {}", response.getCode());
            System.out.println("Returning: " + response.getCode());
        }
        //System.out.println("The execution time is: " + LocalDateTime.now());
    }

    /**
     * This is the method which I would like to execute
     * if there is an exception raised.
     */
    public void AfterThrowingAdvice(LoginException e){
        logger.trace("This exception has been logged: {}", e.toString());
        logger.trace("The error code is: {}", e.getErrorCode());
        System.out.println("This exception has been logged: " + e.toString());
        System.out.println("The error code is: " + e.getErrorCode());
    }
}
