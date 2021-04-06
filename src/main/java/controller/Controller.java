package controller;


import io.javalin.http.Context;
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
}
