package exercise.controller.users;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
private List<Post> posts=Data.getPosts();
    @GetMapping("users/{id}/posts")
    public List<Post> getPostById(@PathVariable("id") Integer id ) {
        return posts.stream().filter(x -> x.getUserId() == id).collect(Collectors.toList());
    }
    @PostMapping("users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@PathVariable("id") Integer id,@RequestBody Post post ) {
        posts.add(new Post(id, post.getSlug(),post.getTitle(),post.getBody() ));
        return posts.get(posts.size()-1);
    }

}
// END
