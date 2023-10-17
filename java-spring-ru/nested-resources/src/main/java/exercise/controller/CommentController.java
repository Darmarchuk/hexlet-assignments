package exercise.controller;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import exercise.ResourceNotFoundException;
import exercise.dto.CommentDto;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.iterators.IteratorIterable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    @GetMapping("/{postId}/comments")
    Iterable<CommentDto> shot(@PathVariable Long postId) {
        Iterable<Comment> comments= commentRepository.findByPostId( postId);
        List<CommentDto> dtos= new ArrayList<>();
        comments.forEach((x)-> {
            dtos.add(new CommentDto(x.getContent()));
        }  );
    return dtos;


    }

    @GetMapping("/{postId}/comments/{commentId}")
    Comment shot(@PathVariable Long postId, @PathVariable Long commentId) {
        return  commentRepository.findByIdAndPostId(commentId,postId).orElseThrow(()-> new ResourceNotFoundException("Comment with id"+commentId+"not found"));
    }

    @PostMapping("/{postId}/comments")
    Iterable<CommentDto> create(@PathVariable Long postId, @RequestBody CommentDto dto) {
        Comment comment=new Comment( );
        comment.setContent(dto.content());
        comment.setPost(postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post with id"+postId+"not found")) );
        commentRepository.save(comment);

        Iterable<Comment> comments =commentRepository.findByPostId(postId);
        List<CommentDto> dtos= new ArrayList<>();
        comments.forEach((x)-> {
            dtos.add(new CommentDto(x.getContent()));
        }  );
        return dtos;
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    void update(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody Comment dto) {
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment with id"+commentId+"not found"));
        comment.setContent(dto.getContent());
        commentRepository.save(comment);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    void delete(@PathVariable Long postId, @PathVariable Long commentId) {
        Comment comment=commentRepository.findByIdAndPostId(commentId,postId).orElseThrow(()->new ResourceNotFoundException("Comment with id"+commentId+"not found"));
        commentRepository.deleteById(commentId);
    }

    }
