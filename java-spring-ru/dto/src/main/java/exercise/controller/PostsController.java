package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

@RestController
@RequestMapping("/posts")

public class PostsController {

    @Autowired PostRepository postRepository;

    @Autowired CommentRepository commentRepository;

    @GetMapping
    List<CommentDTO>show(){
        return  toPostDTO( postRepository.findAll());
    }

    @GetMapping("/{id}")
    PostDTO showById(@PathVariable Long id){
        return mapPostDTO(id);
    }

    List<CommentDTO> toPostDTO(List<Post> posts){
       return posts.stream().
               map(p->new CommentDTO(p.getId(),p.getBody()))
               .collect(Collectors.toList());
    }

    PostDTO mapPostDTO(Long id){
        Post post=postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post with id "+id+" not found") );
        List<Comment> comment = commentRepository.findByPostId(id);
        List<CommentDTO> commentDTOS=comment.stream().map(c->new CommentDTO( c.getId(),c.getBody())).toList();
        PostDTO dto=new PostDTO();
        dto.setComments(commentDTOS);
        dto.setId(post.getId());
        dto.setBody(post.getBody());
        dto.setTitle(post.getTitle());
        return dto;
    }





}