package exercise.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.GenerationType.SEQUENCE;


@Table
@Entity
@NamedEntityGraph(name = "author.findByPost",attributeNodes = @NamedAttributeNode("posts"))
@Getter
@Setter
public class Author {

    @Id
    @SequenceGenerator(name="authorSeq",initialValue = 2)
    @GeneratedValue(strategy = SEQUENCE,generator = "authorSeq")
    private Long authorId;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @Email
    private String email;

    @OneToMany(mappedBy = "postAuthor",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> posts=new ArrayList<>();

}
