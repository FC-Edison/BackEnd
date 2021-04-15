package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edison
 * @create 2021-04-11 19:47
 */
public class DetailedListReply {
    public boolean success;
    public String message;
    public List<DetailedListReplyElement> incomeList = new ArrayList<DetailedListReplyElement>();
    public List<DetailedListReplyElement> outcomeList = new ArrayList<DetailedListReplyElement>();

    public DetailedListReply(boolean success,String message){
        this.success = success;
        this.message = message;
    }

}
