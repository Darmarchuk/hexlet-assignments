package exercise;

import exercise.repository.PostRepository;
import exercise.util.Generator;
import io.javalin.Javalin;
import exercise.controller.PostsController;
import exercise.controller.RootController;
import exercise.util.NamedRoutes;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get(NamedRoutes.rootPath(), RootController::index);

        app.get(NamedRoutes.buildPostPath(), PostsController::build);
        app.post(NamedRoutes.postsPath(), PostsController::create);

        app.get(NamedRoutes.editPostPath("{id}"), PostsController::showEdit);
        app.post(NamedRoutes.postPath("{id}"), PostsController::update );

        app.delete(NamedRoutes.postPath("{id}"), PostsController::delete );

        app.get(NamedRoutes.postsPath(), PostsController::index);
        app.get(NamedRoutes.postPath("{id}"), PostsController::show);


        // BEGIN
        
        // END

        return app;
    }

    public static void main(String[] args) {

        Javalin app = getApp();
        app.start(7070);
        Generator.getPosts().stream().map(x->{PostRepository.save(x); return 1;}).findAny();
    }
}
