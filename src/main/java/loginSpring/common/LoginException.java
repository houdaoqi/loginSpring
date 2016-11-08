package loginSpring.common;

/**
 * Created by lenovo on 10/31/2016.
 */
public class LoginException extends RuntimeException {

    private ResultCode errorCode;

    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }


    public LoginException(String message, Throwable cause,ResultCode errorCode){
        super(message, cause);
        this.errorCode=errorCode;
    }

    public ResultCode getErrorCode(){
        return errorCode;
    }

 }
