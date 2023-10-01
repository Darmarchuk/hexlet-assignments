package exercise;

import exercise.dto.users.UsersPage;
import exercise.model.User;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.Collections;
import java.util.List;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", App::getUsertByTerm);
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    private static void getUsertByTerm(Context context) {
        String fstName = context.queryParamAsClass("term", String.class).getOrDefault("");
        List<User> filterResult;
        if (fstName.length() > 0) {
            filterResult = USERS.stream()
                    .filter(x -> x.getFirstName().toLowerCase().startsWith(fstName.toLowerCase()))
                    .toList();
        } else filterResult = USERS;
        UsersPage page = new UsersPage(filterResult,fstName.trim());
        context.render("users/index.jte", Collections.singletonMap("page", page));


    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
