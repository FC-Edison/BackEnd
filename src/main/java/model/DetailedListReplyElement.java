package model;

/**
 * @author Edison
 * @create 2021-04-11 21:00
 */
public class DetailedListReplyElement{
    public long timeStamp;
    public double amount;
    public String type;
    public String remarks;
    public boolean isOutcome;
    public DetailedListReplyElement(long timeStamp,double amount,String type,String remarks,boolean isOutcome){
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.type = type;
        this.remarks = remarks;
        this.isOutcome = isOutcome;
    }
}
