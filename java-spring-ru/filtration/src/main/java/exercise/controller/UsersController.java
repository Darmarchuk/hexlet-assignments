package exercise.controller;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.service.SearchCriteria;
import exercise.service.UserSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;

// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    @Autowired
    private final UserRepository userRepository;

        @GetMapping
    public List<User> getuser(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName ){
        List<UserSpecification> spec=new ArrayList<>();
        if(firstName!=null) {
            UserSpecification uspc = new UserSpecification(new SearchCriteria<String>("firstName", firstName));
            spec.add(uspc);
        }
        if(lastName!=null) {
            UserSpecification uspc = new UserSpecification(new SearchCriteria<String>("lastName", lastName));
            spec.add(uspc);
        }
        Specification<User> totalSpec=null;

        for (UserSpecification t: spec)
        {
            if(totalSpec==null) totalSpec=t;
            else totalSpec =   totalSpec.and(t);

        }
        return  userRepository.findAll(totalSpec);



    }
}

