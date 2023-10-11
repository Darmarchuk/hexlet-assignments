package exercise.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.GenerationType.SEQUENCE;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.GeneratedValue;


@Entity
@Table(name = "posts")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "posts_seq")
    @SequenceGenerator(name="posts_seq",initialValue = 7)
    private Long id;

    @NotEmpty
    @Size(min=2)
    private String title;
    @Size(min=7)
    private String body;

    @JsonBackReference
    @ManyToOne
    @JoinColumn( name="authorId")
    private Author postAuthor;
}
