package exercise.controller;

import exercise.dto.AuthorCreateDto;
import exercise.dto.AuthorDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorMapper authorMapper;

    @GetMapping
    public List<AuthorDTO> show(){
        return  authorMapper.put( authorRepository.findAll());

    }

    @GetMapping("/{id}")
    public AuthorDTO showAuthorById(@PathVariable Long id){
       return authorMapper.put( authorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("author with id "+id+" not found")));

    }
    @PostMapping
    public AuthorDTO create(@RequestBody AuthorCreateDto dto){
        Author author = authorMapper.put(dto);
        authorRepository.save(author);
        return  authorMapper.put(author);

    }
}
