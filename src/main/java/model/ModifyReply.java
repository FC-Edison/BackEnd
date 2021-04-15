package model;

/**
 * @author Edison
 * @create 2021-04-13 18:19
 */
public class ModifyReply {
    public boolean success;
    public String message;
    public ModifyReply(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
