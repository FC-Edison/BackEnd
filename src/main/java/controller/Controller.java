package controller;


import io.javalin.http.Context;
import model.*;
import sql.DatabaseHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Edison
 * @create 2021-04-06 19:15
 */
public class Controller {
    private static int identification(Context ctx) {
        String accountName = ctx.header("account_name");
        String sql = "SELECT id FROM user WHERE account_name = " + DatabaseHelper.sqlString(accountName) + ";";
        ResultSet res = DatabaseHelper.getInstance().exeSqlQuery(sql);
        if (res == null) {
            return -1;
        } else {
            try {
                if (!(res.first())) {
                    return -1;
                } else {
                    return res.getInt("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
    }

    private static void bookKeeping(Context ctx, String str,boolean isOutcome) {
        BookKeepingReply reply = new BookKeepingReply(true, "");
        int userId = identification(ctx);

        String timeStamp = ctx.formParam("time_stamp");
        double amounts = Double.parseDouble(ctx.formParam("amount"));
        String type = ctx.formParam("type");
        String remarks = ctx.formParam("remarks");
        if (type == null || amounts == 0) {
            reply.success = false;
            reply.message = "输入信息错误";
        } else {
            String sql = "INSERT INTO " + str + " ( time_stamp, amount, type, remarks, user_id ,is_outcome)\n" +
                    "                                                            VALUES\n" +
                    "                    ( " + timeStamp + ", " + amounts + ", \"" + type + "\", \"" + remarks + "\", " + userId + ", " + isOutcome + " );";
            DatabaseHelper.getInstance().exeSqlUpdate(sql);

        }
        ctx.json(reply);
    }


    public static void login(Context ctx) {
        LoginReply reply = new LoginReply(true, "");
        String accountName = ctx.formParam("account_name");
        String sql = "SELECT * FROM user WHERE account_name = " + DatabaseHelper.sqlString(accountName);
        ResultSet res = DatabaseHelper.getInstance().exeSqlQuery(sql);
        if (res == null) {
            reply.message = "未知错误";
            reply.success = false;
        } else {
            try {
                if (!res.first()) {
                    sql = "INSERT INTO user (account_name)\n" +
                            "                       VALUES\n" +
                            "                       (" + DatabaseHelper.sqlString(accountName) + ");";
                    DatabaseHelper.getInstance().exeSqlUpdate(sql);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                reply.message = "未知错误";
                reply.success = false;
            }
        }
        ctx.json(reply);
    }

    public static void outcome(Context ctx) {
        bookKeeping(ctx, "outcome",true);
    }

    public static void income(Context ctx) {
        bookKeeping(ctx, "income",false);
    }

    public static void detailedList(Context ctx) {
        DetailedListReply reply = new DetailedListReply(true, "");
        int userId = identification(ctx);
        String beginTimeStamp = ctx.queryParam("begin_time_stamp");
        String endTimeStamp = ctx.queryParam("end_time_stamp");

        String incomeSql = "SELECT `time_stamp`,`amount`,`type`,`remarks` FROM `income` WHERE `user_id` = " + userId + " AND `time_stamp` >= " + beginTimeStamp + " AND `time_stamp` < " + endTimeStamp + ";";
        String outcomeSql = "SELECT `time_stamp`,`amount`,`type`,`remarks` FROM `outcome` WHERE `user_id` = " + userId + " AND `time_stamp` >= " + beginTimeStamp + " AND `time_stamp` < " + endTimeStamp + ";";
        ResultSet incomeRes = DatabaseHelper.getInstance().exeSqlQuery(incomeSql);
        ResultSet outcomeRes = DatabaseHelper.getInstance().exeSqlQuery(outcomeSql);

        if(incomeRes == null || outcomeRes == null){
            reply.success = false;
            reply.message = "未知错误";
        }else{
            try{
                while (outcomeRes.next()){
                    reply.outcomeList.add(new DetailedListReplyElement(outcomeRes.getLong("time_stamp"),outcomeRes.getDouble("amount"),outcomeRes.getString("type"),outcomeRes.getString("remarks"),true));
                }
                while(incomeRes.next()){
                    reply.incomeList.add(new DetailedListReplyElement(incomeRes.getLong("time_stamp"),incomeRes.getDouble("amount"),incomeRes.getString("type"),incomeRes.getString("remarks"),false));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        ctx.json(reply);
    }

    public static void deleteRecord(Context ctx){
        DeleteReply reply = new DeleteReply(true,"");
        int userId = identification(ctx);
        boolean isOutcome = Boolean.parseBoolean(ctx.formParam("is_outcome"));
        String timeStamp = ctx.formParam("time_stamp");

        String sql;
        if(isOutcome){
            sql = "DELETE FROM `outcome` WHERE (`time_stamp` = " + timeStamp + " AND `user_id` = " + userId + ");";
        }else{
            sql = "DELETE FROM `income` WHERE (`time_stamp` = " + timeStamp + " AND `user_id` = " + userId + ");";
        }
        int i = DatabaseHelper.getInstance().exeSqlUpdate(sql);
        if(i <= 0){
            reply.success = false;
            reply.message = "删除失败";
        }

        ctx.json(reply);
    }

    public static void modifyRecord(Context ctx){
        ModifyReply reply = new ModifyReply(true,"");
        int userId = identification(ctx);
        boolean isOutcome = Boolean.parseBoolean(ctx.formParam("is_outcome"));
        String oldTimeStamp = ctx.formParam("old_time_stamp");
        String timeStamp = ctx.formParam("time_stamp");
        double amount = Double.parseDouble(ctx.formParam("amount"));
        String type = ctx.formParam("type");
        String remarks = ctx.formParam("remarks");

        String sql;
        if(isOutcome){
            sql = "UPDATE `outcome` SET `time_stamp`=" + timeStamp + ", `amount`=" + amount + ", `type`=\'" + type + "\', `remarks`=\'" + remarks + "\' WHERE `time_stamp` = " + oldTimeStamp + " AND `user_id` = " + userId + " ;";
        }else{
            sql = "UPDATE `income` SET `time_stamp`=" + timeStamp + ", `amount`=" + amount + ", `type`=\'" + type + "\', `remarks`=\'" + remarks + "\' WHERE `time_stamp` = " + oldTimeStamp + " AND `user_id` = " + userId + " ;";

        }
        int i = DatabaseHelper.getInstance().exeSqlUpdate(sql);

        if(i <= 0){
            reply.success = false;
            reply.message = "更改失败";
        }

        ctx.json(reply);
    }
}
