import controller.Controller;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import sql.DatabaseHelper;

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

        app.post("/book-keeping/outcome",Controller::outcome);

        app.post("/book-keeping/income",Controller::income);

        app.post("/book-keeping/delete-record",Controller::deleteRecord);

        app.post("/book-keeping/modify-record",Controller::modifyRecord);

        app.get("/detailed-list",Controller::detailedList);

        app.config.addStaticFiles("/static", "static", Location.EXTERNAL);
    }
}


