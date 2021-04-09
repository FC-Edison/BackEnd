package controller;


import io.javalin.http.Context;
import model.BookKeepingReply;
import model.LoginReply;
import sql.DatabaseHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Edison
 * @create 2021-04-06 19:15
 */
public class Controller {
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
                            "                       ("+ DatabaseHelper.sqlString(accountName) +");";
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
    public static void bookKeeping(Context ctx) {
        BookKeepingReply reply = new BookKeepingReply(true,"");
        String accountName = ctx.header("account_name");
        String  timeStamp = ctx.formParam("time_stamp");
        double amounts = Double.parseDouble(ctx.formParam("out_come_amount"));
        String type = ctx.formParam("type");
        String remarks = ctx.formParam("remarks");
        if (accountName == null || type == null || remarks == null) {
            reply.success = false;
            reply.message = "获取输入信息错误";
        }else {
            String sql = "SELECT id FROM user WHERE account_name = " + DatabaseHelper.sqlString(accountName) + ";";
            ResultSet res = DatabaseHelper.getInstance().exeSqlQuery(sql);
            if (res == null) {
                reply.success = false;
                reply.message = "查询数据库错误";
            }else {
                try {
                    if (res.first()) {
                        int userId = res.getInt("id");
                        sql = "INSERT INTO outcome ( time_stamp, out_come_amount, type, remarks, user_id )\n" +
                                "                                                            VALUES\n" +
                                "                    ( " + timeStamp + ", " + amounts + ", \"" + type + "\", \"" + remarks + "\", " + userId + " );";
                        DatabaseHelper.getInstance().exeSqlUpdate(sql);
                    }else{
                        reply.success = false;
                        reply.message = "找不到用户名";
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                    reply.success = false;
                    reply.message = "数据库错误";
                }
            }
        }
        ctx.json(reply);
    }
}
