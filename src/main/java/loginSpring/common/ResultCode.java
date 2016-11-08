package loginSpring.common;

/**
 * Created by lenovo on 11/1/2016.
 */
public enum ResultCode {
    PASS("0000"),
    NO_USER("0001"),
    INVALID_PASS("0002");

    private String code;

    ResultCode(String code) {
        this.code = code;
    }
}
