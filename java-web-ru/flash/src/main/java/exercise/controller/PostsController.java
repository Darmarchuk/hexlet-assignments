package exercise.controller;

import java.util.Collections;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.posts.BuildPostPage;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;
import org.jetbrains.annotations.NotNull;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", Collections.singletonMap("page", page));
    }

    // BEGIN

    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }

    public static void create(@NotNull Context ctx) {
        try {
            String name = ctx.formParamAsClass("name", String.class)
                    .check(x -> x.length() > 2, "Длинна называния должна быть больше 2 симоволов")
                    .get();
            String body = ctx.formParamAsClass("body", String.class)
                    .check(x -> x.length() > 1, "Содержание должна быть больше 10 симоволов")
                    .get();

            PostRepository.save(new Post(name, body));
            ctx.sessionAttribute("flash", "Пост был успешно создан!");
            ctx.redirect(NamedRoutes.postsPath());

        } catch (ValidationException ve) {
            String name = ctx.formParamAsClass("name", String.class).get();
            String body = ctx.formParamAsClass("body", String.class).get();
            BuildPostPage page = new BuildPostPage(name, body, ve.getErrors());

        }

    }

    public static void index(@NotNull Context ctx) {
        String flash=ctx.consumeSessionAttribute("flash");

        PostsPage postsPage=new PostsPage( PostRepository.getEntities());
        postsPage.setFlash(flash);
        ctx.render("posts/index.jte" , Collections.singletonMap("page",postsPage));

    }
}
