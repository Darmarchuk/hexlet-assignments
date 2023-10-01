package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Collections;
import java.util.Locale;

import exercise.model.User;
import exercise.dto.users.UsersPage;
import exercise.repository.UserRepository;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        // BEGIN
        app.get("/users/build",App::DisplayCreateUser);
        app.post("/users",App::createNewUser);
        // END

        return app;
    }

    private static void createNewUser(Context context) {
        String firstName=context.formParamAsClass("firstName",String.class).get().toLowerCase().trim();
        firstName=StringUtils.capitalize(firstName);
        String lastName=context.formParamAsClass("lastName",String.class).get().toLowerCase().trim();
        lastName=StringUtils.capitalize(lastName);
        String email=context.formParamAsClass("email",String.class).get().toLowerCase(Locale.ROOT).trim();
        String password=context.formParamAsClass("password",String.class).get();
        password=Security.encrypt(password);
        UserRepository.save(new User(firstName,lastName,email,password ) );
        context.redirect("/users");

    }

    private static void DisplayCreateUser(Context context) {
        context.render("users/build.jte");
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
