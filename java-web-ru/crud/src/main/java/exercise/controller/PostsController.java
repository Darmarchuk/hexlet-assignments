package exercise.controller;

import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;

public class PostsController {
    public static void getPostById(@NotNull Context context) {
        Long id = context.pathParamAsClass("id", Long.class).get();
        Post post = PostRepository.find(id).orElse(null);
        PostPage page = new PostPage(post);
//        if(post==null) {
//            throw  new NotFoundResponse();
//        };
        context.render("posts/show.jte", Collections.singletonMap("page", page));

    }

    public static void getPosts(@NotNull Context context) {
        Integer pageNumber =context.queryParamAsClass("page",Integer.class).getOrDefault(1);
        PostsPage page = new PostsPage(PostRepository.getEntriesByPage(pageNumber-1),pageNumber);
        context.render("posts/index.jte", Collections.singletonMap("page", page));
    }

}
