package model;

/**
 * @author Edison
 * @create 2021-04-07 13:55
 */
public class BookKeepingReply {
    public boolean success;
    public String message;
    public BookKeepingReply(boolean success, String message){
        this.success = success;
        this.message = message;
    }
}
