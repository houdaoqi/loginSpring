package loginSpring.common;

/**
 * Created by lenovo on 11/1/2016.
 */
public class LoginResponse {
    private ResultCode code;
    private long ID;
    private String name;

    public LoginResponse(ResultCode code) {
        this.code = code;
    }

    public LoginResponse(ResultCode code, String name) {
        this.code = code;
        this.name = name;
    }

    public LoginResponse(ResultCode code, long ID, String name) {
        this.code = code;
        this.ID = ID;
        this.name = name;
    }

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
