package exercise.controller;

import java.util.Collections;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.Security;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import org.jetbrains.annotations.NotNull;

public class SessionsController {

    public static void createSession(@NotNull Context ctx) {
        ctx.render("build.jte");
    }

    public static void registerUser(@NotNull Context context) {
        String name=context.formParam("name");
        String passwd= Security.encrypt(context.formParam("password"));
        User user = UsersRepository.findByName(name);
        if(user!=null && user.getPassword().equals(passwd)){
            context.sessionAttribute("name",name);
            context.redirect("/");
        }
        else throw new NotFoundResponse("Wrong name or password");


    }

    public static void mainPage(@NotNull Context context) {
        MainPage page=new MainPage(context.sessionAttribute("name") );
        context.render("index.jte",Collections.singletonMap("page",page ) );
    }

    public static void logoff(@NotNull Context context) {
        context.sessionAttribute("name",null);
        context.redirect("/");
    }

    // BEGIN
    
    // END
}
