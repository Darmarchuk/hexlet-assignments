package exercise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;


@Table
@Entity
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long authorId;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @Email
    private String email;

    @OneToMany(mappedBy = "postAuthor")
    private List<Post> posts;

}
