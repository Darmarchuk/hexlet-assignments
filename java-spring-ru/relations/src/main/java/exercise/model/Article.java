package exercise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Article implements BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long articleId;

    String name;
    String body;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "categoryId")
    private Category category;

}
