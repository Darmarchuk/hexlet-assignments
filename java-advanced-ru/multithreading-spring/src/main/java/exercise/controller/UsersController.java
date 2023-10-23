package exercise.controller;

import exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import exercise.service.UserService;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping(path="/{id}")
    public Mono<User> getUserById(@PathVariable Long id){
        try {
            return userService.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public Mono<User>create(@RequestBody User user){
       return userService.save(user);
    }


    @PatchMapping(path="/{id}")
    public Mono<User>update(@PathVariable Long id,@RequestBody Mono<User> user){
        return userService.update(id,user);
    }

    @DeleteMapping(path="/{id}")
    public Mono<Void>  delete(@PathVariable Long id){
        return userService.delete(id);

    }
}
