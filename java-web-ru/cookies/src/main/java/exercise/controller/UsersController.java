package exercise.controller;

import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import java.util.Collections;
import exercise.repository.UserRepository;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    public static void show(@NotNull Context context) {
        Long userId =context.pathParamAsClass("id",Long.class).get();
        User user=UserRepository.find(userId).orElseThrow(()-> new NotFoundResponse("User not found"));
        String tokenAddr =user.getToken();
        String token= context.cookie("token");
        if (tokenAddr.equals(token)) {
            context.render("users/show.jte",Collections.singletonMap("user",user) );
        }
    }

    public static void createUser(@NotNull Context context) {
        String firstName=context.formParamAsClass("firstName",String.class).get();
        String lastName=context.formParamAsClass("lastName",String.class).get();;

        String email=context.formParamAsClass("email",String.class).get();;
        String password=context.formParamAsClass("password",String.class).get();;
        String token =Security.generateToken();
        User user=new User(firstName,lastName,email,password,token);
        UserRepository.save(user);
        context.cookie("token",token);
        context.redirect(NamedRoutes.userPath(user.getId()));
    }

    // BEGIN
    
    // END
}
