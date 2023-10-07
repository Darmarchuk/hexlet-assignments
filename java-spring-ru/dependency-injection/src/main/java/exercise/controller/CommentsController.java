package exercise.controller;

import exercise.model.Post;
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

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;
@RequestMapping("/comments")
@RestController
public class CommentsController{

    @Autowired
    CommentRepository commentRepository;



    @GetMapping
    public List<Comment> showAll(){
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment showById(@PathVariable Long id){
        return commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment with id "+id+" not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment post){
        return commentRepository.save(post);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment update(@PathVariable Long id, @RequestBody Comment comment){
        Comment newPost=commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment with id "+id+" not found"));
        newPost.setBody(comment.getBody());
        return commentRepository.save(newPost);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        commentRepository.deleteById(id);
    }




}