package exercise.dto;

import exercise.model.Category;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDto{
    Long articleId;

    String name;
    String body;

    private Category category;
}