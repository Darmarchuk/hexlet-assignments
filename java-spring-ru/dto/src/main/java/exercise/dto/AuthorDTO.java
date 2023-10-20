package exercise.dto;

import exercise.model.Post;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
public class AuthorDTO {

    private Long authorId;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @Email
    private String email;

    private List<AuthorPostsDTO> posts;

}
