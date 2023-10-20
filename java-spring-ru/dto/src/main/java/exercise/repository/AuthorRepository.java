package exercise.repository;

import exercise.model.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long>, AuthorRepositoryCustom, JpaSpecificationExecutor<Author> {


//    @EntityGraph(value="author.findByPost")
    @EntityGraph(attributePaths = {"posts"})
    List<Author> findByLastName(String lastname);

    @EntityGraph(value="author.findByPost")
    @Query("select a from Author a where a.firstName=:firstName ")
    List<Author> findByFirstName(String firstName);

}
