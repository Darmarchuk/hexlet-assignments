package exercise;

import io.javalin.Javalin;
import exercise.controller.SessionsController;
import exercise.util.NamedRoutes;


public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get(NamedRoutes.buildSessionPath(),SessionsController::createSession );
        app.post(NamedRoutes.loginPath(),SessionsController::registerUser);
        app.get(NamedRoutes.rootPath(), SessionsController::mainPage );
        app.post(NamedRoutes.logoutPath(),SessionsController::logoff);

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
