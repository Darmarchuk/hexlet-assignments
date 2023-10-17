package exercise.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class WelcomeController {

    @GetMapping("/")
    public String root() {
        return "Welcome to Spring";
    }
}
