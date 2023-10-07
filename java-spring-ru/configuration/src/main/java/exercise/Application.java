package exercise;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.beans.factory.annotation.Autowired;

import exercise.model.User;
import exercise.component.UserProperties;

@SpringBootApplication
@RestController
public class Application {

    @Autowired
    private  UserProperties admins;

    // Все пользователи
    private List<User> users = Data.getUsers();

    // BEGIN
    @GetMapping("/admins")
    public Set<String> getAdmins() {

        return admins.getAdmins().stream()
                .map(x->users.stream().filter(u->u.getEmail().equals(x))
                        .map(User::getName).collect(Collectors.toSet()))
                .flatMap(Collection::stream)
                .sorted().collect(Collectors.toSet());
    }
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }


    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
            .filter(u -> u.getId() == id)
            .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
