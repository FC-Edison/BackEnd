package model;

/**
 * @author Edison
 * @create 2021-04-06 20:37
 */
public class LoginReply {
    public boolean success;
    public String message;
    public LoginReply(boolean success, String message){
        this.success = success;
        this.message = message;
    }
}
