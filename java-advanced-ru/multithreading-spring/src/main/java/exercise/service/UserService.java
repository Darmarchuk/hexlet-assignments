package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findById(Long id) throws Exception {
        Mono<User> user=  userRepository.findById(id)                ;
        if (user==null) throw new Exception("Not found");
        return user;
    };

    public Mono<User>save(User user ){return userRepository.save(user) ;}


    public Mono<User>update(Long id,final Mono<User> user){
        Mono<User> userBase= null;
        try {
            userBase = findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Mono<User>upd= userBase.flatMap(B->
                user.map(U-> {
                    B.setEmail(U.getEmail());
                    B.setFirstName(U.getFirstName());
                    B.setLastName(U.getLastName());
                    return  B;
                }));

        return upd.flatMap(x-> userRepository.save(x));
    }

    public Mono<Void> delete(Long userid){

        try {
            Mono<User> user=findById(userid);
            return userRepository.deleteById(userid);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
