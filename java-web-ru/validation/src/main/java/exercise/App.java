package exercise;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import java.util.List;
import exercise.model.Article;
import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.NewArticlePage;
import java.util.Collections;

import exercise.repository.ArticleRepository;
import org.jetbrains.annotations.NotNull;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", Collections.singletonMap("page", page));
        });
        
        app.get("/articles/new", App::RenderNewArticleForm );
        app.post("/articles", App::CreateNewArticle );
        
        // BEGIN
        
        // END

        return app;
    }

    private static void CreateNewArticle(@NotNull Context context) {
        String content;
        String title;
        try{
            title=context.formParamAsClass("title", String.class )
                    .check(x->x.length()>2,"Название не должно быть короче двух символов")
                    .check(t-> !ArticleRepository.existsByTitle(t),"Статья с таким названием уже существует")
                    .getOrDefault("");

            content=context.formParamAsClass("content", String.class )
                    .check(x-> x.length()>10,"Статья должна быть не короче 10 символов" )
                    .get();

            ArticleRepository.save(new Article(title,content ));
            context.redirect("/articles");

        }
        catch (ValidationException e ){
            content=context.formParamAsClass("content", String.class ).get();
            title=context.formParamAsClass("title", String.class ).get();
            NewArticlePage page=new NewArticlePage(title,content,e.getErrors());
            context.status(422);
            context.render("articles/build.jte",Collections.singletonMap("page",page ) );

        }
    }


    private static void RenderNewArticleForm(@NotNull Context context) {
        NewArticlePage page=new NewArticlePage();
        context.render("articles/build.jte",Collections.singletonMap("page",page ) );
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
