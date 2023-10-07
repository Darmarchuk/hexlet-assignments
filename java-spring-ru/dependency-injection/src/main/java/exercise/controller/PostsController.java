package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/posts")
public class PostsController{

    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;



    @GetMapping
    public List<Post> showAll(){
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post showById(@PathVariable Long id){
        return postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post with id "+id+" not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post){
        return postRepository.save(post);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post update(@PathVariable Long id, @RequestBody Post post){
        Post newPost=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post with id "+id+" not found"));
        newPost.setBody(post.getBody());
        newPost.setTitle(post.getTitle());

        return postRepository.save(newPost);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        commentRepository.deleteByPostId(id);
        postRepository.deleteById(id);
    }



}