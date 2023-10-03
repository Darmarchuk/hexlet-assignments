package exercise.controller;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import org.jetbrains.annotations.NotNull;

public class PostsController {
    public static void getPostById(@NotNull Context context) {
        Long id = context.pathParamAsClass("id", Long.class).get();
        Post post = PostRepository.find(id).orElse(null);
        PostPage page = new PostPage(post);
        if(post==null) {
            throw  new NotFoundResponse();
        };

        context.render("posts/show.jte", Collections.singletonMap("page", page));

    }

    public static void getPosts(@NotNull Context context) {
        Integer pageNumber =context.pathParamAsClass("page",Integer.class).getOrDefault(0);
        PostsPage page = new PostsPage(PostRepository.getEntriesByPage(pageNumber),pageNumber);

        context.render("posts/index.jte", Collections.singletonMap("page", page));
    }


    // BEGIN

    // END
}
