package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edison
 * @create 2021-04-21 19:12
 */
public class DetailedChartReply {
    public boolean success;
    public String message;
    public List<DetailedChartReplyElement> incomeList = new ArrayList<DetailedChartReplyElement>();
    public List<DetailedChartReplyElement> outcomeList = new ArrayList<DetailedChartReplyElement>();

    public DetailedChartReply(boolean success,String message){
        this.success = success;
        this.message = message;
    }

}
