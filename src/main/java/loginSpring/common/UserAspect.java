package loginSpring.common;

/**
 * Created by lenovo on 10/28/2016.
 */
//@Aspect
public class UserAspect {
    //@Before("allUserDaoSqliteMethods()")
    public void loggingAdvice(){
        System.out.println("The method in UserDaoSqliteImpl is called!");
    }

    //@Pointcut("within(loginSpring.dao.impl.UserDaoSqliteImpl)")
    public void allUserDaoSqliteMethods(){}
}
