package exercise;

import io.javalin.Javalin;
import java.util.List;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users/{id}",App::getUserById );
        app.get("/users",App::getUsers);


        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    private static void getUsers(@NotNull Context context) {
        UsersPage usersPage=new UsersPage(USERS);
        context.render("users/show.jte",Collections.singletonMap("users",usersPage ) );
    }

    private static void getUserById(@NotNull Context context) {
        long id= context.pathParamAsClass("id",Long.class).get();
        User user=USERS.stream()
                .filter(x->x.getId()==id)
                .findFirst()
                .orElseThrow(()->new NotFoundResponse("User not found"));
        UserPage userPage=new UserPage(user);
        context.render("users/index.jte",Collections.singletonMap("user",userPage) );
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
