package exercise;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.NotFoundResponse;

import java.util.List;
import java.util.Map;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}",App::getCompanyById );
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });
        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    private static void getCompanyById(Context ctx) {
        var id=ctx.pathParamAsClass("id",String.class).get();

        for (Map<String,String> itm  : COMPANIES) {
            if(itm.get("id").equals(id)) {
                ctx.json(itm );
                return ;
            }
        }
        throw  new NotFoundResponse("Company not found");
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
