package model;

/**
 * @author Edison
 * @create 2021-04-13 16:32
 */
public class DeleteReply {
    public boolean success;
    public String message;
    public DeleteReply(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
