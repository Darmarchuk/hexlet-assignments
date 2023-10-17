package exercise.repository;

import exercise.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Optional<Comment> findByIdAndPostId(Long Id,Long postId );

    Iterable<Comment> findByPostId(Long postId);
}
