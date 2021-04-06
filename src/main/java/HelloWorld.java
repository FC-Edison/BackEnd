import controller.Controller;
import io.javalin.Javalin;
import sql.DatabaseHelper;

import java.sql.ResultSet;


/**
 * @author Edison
 * @create 2021-04-03 13:42
import io.javalin.Javalin;
 */

public class HelloWorld {
    public static void main(String[] args) {
        DatabaseHelper.getInstance().connect("localhost",3306,"pocket_book","root","root");

        Javalin app = Javalin.create().start(80);

        app.post("/login", Controller::login);
    }
}


