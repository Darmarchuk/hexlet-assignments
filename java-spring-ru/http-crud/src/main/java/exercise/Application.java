package exercise;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping(value = "/posts")
    Optional<List<Post>>getPosts(@RequestParam(defaultValue = "1") int page ,@RequestParam(defaultValue = "5") int pageSize ){
        return Optional.of(posts.stream()
                .skip(pageSize*(page-1))
                .limit(pageSize).collect(Collectors.toList()));
    }

    @GetMapping("/posts/{id}")
    Optional<Post> getPostById(@PathVariable String id ){
        return posts.stream()
                .filter(x-> x.getId().equals(id))
                .findFirst();
    }

    @PostMapping("/posts")
    Post createNewPost(@RequestBody Post newpost ){
        Post postAdd=new Post(newpost.getId(), newpost.getTitle(), newpost.getBody());
        posts.add(postAdd);
        return postAdd;
    }

    @DeleteMapping("/posts/{id}")
    void removePost(@PathVariable String id){
        posts.removeIf(x-> x.getId().equals(id) );
    }

    @PutMapping("/posts/{id}")
    Post updatePost(@PathVariable String id, @RequestBody Post post ){
        var updPost= posts.stream().filter(x-> x.getId().equals(id) ).findFirst();
        if(updPost.isPresent()){
        updPost.get().setBody(post.getBody());
        updPost.get().setTitle(post.getTitle());
        }
        return post;
    }

}
