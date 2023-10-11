package exercise.controller;

import exercise.dto.PostCreateDTO;
import exercise.dto.PostListDTO;
import exercise.mapper.CommentMapper;
import exercise.mapper.PostMapper;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import jakarta.validation.Valid;
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

    @Autowired
    PostMapper postMapper;
    @Autowired
    CommentMapper commentMapper;

    @GetMapping
    List<PostListDTO>show(){
        return   postRepository.findAll().stream().map(p->postMapper.put(p)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    PostDTO showById(@PathVariable Long id){
        return mapPostDTO(id);
    }

    @PostMapping
    PostDTO create(@Valid @RequestBody PostCreateDTO postDTO){
        Post post= postMapper.put(postDTO);
        postRepository.save(post);
        return  postMapper.put(post,null);
    }

    @PutMapping("/{id}")
    PostDTO update(@PathVariable Long id,@Valid @RequestBody PostDTO post ){
        Post updPost=postMapper.put(post);
        PostDTO u=postMapper.put(postRepository.save(updPost),null);
        return  mapPostDTO(id);
    }

    List<CommentDTO> toPostDTO(List<Post> posts){
       return posts.stream().
               map(p->new CommentDTO(p.getId(),p.getBody()))
               .collect(Collectors.toList());
    }

    PostDTO mapPostDTO(Long id){
        Post post=postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post with id "+id+" not found") );
        List<CommentDTO> commentDTOS=commentRepository.findByPostId(id)
                .stream().map(c->commentMapper.map(c)).collect(Collectors.toList());
        PostDTO postDTO=postMapper.put(post,commentDTOS);
        return postDTO;
    }





}