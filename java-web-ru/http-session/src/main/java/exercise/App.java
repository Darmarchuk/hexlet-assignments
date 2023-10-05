package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users",ctx->{
            int page=ctx.queryParamAsClass("page", Integer.class ).getOrDefault(1)-1;
            int per=ctx.queryParamAsClass("per", Integer.class ).getOrDefault(5);
            var res=USERS.stream().skip(page*per).limit(per).collect(Collectors.toList());
            ctx.json(res);
        } );
        
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
